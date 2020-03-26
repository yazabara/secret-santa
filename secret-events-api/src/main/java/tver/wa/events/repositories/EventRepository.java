package tver.wa.events.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import tver.wa.events.model.Event;

import java.util.UUID;

@Repository
public interface EventRepository extends ReactiveMongoRepository<Event, UUID> {
}
