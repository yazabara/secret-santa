package tver.wa.services.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.exceptions.EventNotFoundException;
import tver.wa.model.secret.santa.Event;
import tver.wa.repositories.UUIDGenerator;
import tver.wa.repositories.event.EventRepository;
import tver.wa.services.client.ClientService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService {

    private final UUIDGenerator uuidGenerator;
    private final EventRepository eventRepository;
    private final ClientService clientService;

    @Override
    public Flux<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Mono<Event> getById(UUID uuid) {
        return eventRepository
                .findById(uuid)
                .switchIfEmpty(
                        Mono.error(new EventNotFoundException("Event not found for uuid = " + uuid))
                );
    }


    @Override
    public Mono<Event> create(String clientKey, Mono<Event> event) {
        return Flux
                .combineLatest(event, clientService.getByPublicKey(clientKey), (e, client) -> e
                        .toBuilder()
                        .uuid(uuidGenerator.generate())
                        .creator(client.getUuid())
                        .build()
                )
                .next()
                .flatMap(entity -> {
                    log.info(String.format("New event with uuid = %s will be created", entity));
                    return eventRepository.save(entity);
                });
    }

    @Override
    public Mono<Event> update(UUID uuid, Mono<Event> event) {
        return getById(uuid)
                .map(old -> {
                    Event newEvent = event.block();
                    return newEvent == null ? old : old.toBuilder()
                            .title(newEvent.getTitle())
                            .description(newEvent.getDescription())
                            .start(newEvent.getStart())
                            .end(newEvent.getEnd())
                            .participants(newEvent.getParticipants())
                            .build();
                })
                .flatMap(entity -> {
                    log.info(String.format("Event with uuid = %s will be updated", uuid));
                    return eventRepository.save(entity);
                });
    }

    @Override
    public Mono<Event> delete(UUID uuid) {
        return getById(uuid)
                .flatMap(event -> {
                    log.info(String.format("Event with uuid = %s will be deleted", uuid));
                    return eventRepository.deleteById(event.getUuid()).thenReturn(event);
                });
    }
}
