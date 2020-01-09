package tver.wa.services.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.exceptions.EventNotFoundException;
import tver.wa.model.secret.santa.Event;
import tver.wa.repositories.event.EventRepository;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService {

    private final EventRepository eventRepository;

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
    public Mono<Event> create(Mono<Event> event) {
        return event.flatMap(eventRepository::save);
    }

    @Override
    public Mono<Event> update(Event event) {
        return getById(event.getUuid())
                .flatMap(e -> eventRepository.save(event));
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
