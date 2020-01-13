package tver.wa.repositories.event;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import tver.wa.model.secret.santa.Event;

import java.util.UUID;

@Repository
public interface EventRepository extends ReactiveMongoRepository<Event, UUID> {
}
