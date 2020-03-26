package tver.wa.client.repositories;

import org.springframework.stereotype.Repository;

import tver.wa.events.model.Event;
import java.util.UUID;

@Repository
public interface EventRepository extends ReactiveMongoRepository<Event, UUID> {
}
