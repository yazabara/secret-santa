package tver.wa.services.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.Event;
import tver.wa.repositories.event.EventRepository;
import tver.wa.repositories.user.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public Flux<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Mono<Event> getById(UUID uuid) {
        return eventRepository.findById(uuid);
    }

    @Override
    public Mono<Event> create(Mono<Event> event) {
        return event.flatMap(eventRepository::save);
    }

    @Override
    public Mono<Event> update(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Mono<UUID> delete(UUID uuid) {
        return eventRepository.deleteById(uuid).thenReturn(uuid);
    }
}
