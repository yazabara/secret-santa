package tver.wa.events.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.client.exceptions.EventNotFoundException;
import tver.wa.client.repositories.EventRepository;
import tver.wa.common.UuidGenerator;
import tver.wa.events.model.Event;
import tver.wa.events.services.EventService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService {

    private final UuidGenerator uuidGenerator;
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
                    log.info(String.format("New event with uuid = %s will be created", entity.getUuid()));
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
