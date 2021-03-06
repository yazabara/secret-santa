package tver.wa.repositories.client;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.Client;

import java.util.UUID;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, UUID> {

    Flux<Client> findByPublicKey(String publicKey);
}
