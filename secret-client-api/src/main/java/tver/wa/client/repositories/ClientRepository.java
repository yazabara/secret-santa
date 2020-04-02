package tver.wa.client.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import tver.wa.client.model.Client;

import java.util.UUID;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, UUID> {

    Flux<Client> findByName(String name);
}
