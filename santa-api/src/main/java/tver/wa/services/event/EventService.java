package tver.wa.services.event;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.Event;

import java.util.UUID;

@Service
public interface EventService {

    Flux<Event> getAll();

    Mono<Event> getById(UUID uuid);

    Mono<Event> create(Mono<Event> event);

    Mono<Event> update(Event event);

    Mono<Event> delete(UUID uuid);
}
