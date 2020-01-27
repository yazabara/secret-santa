package tver.wa.services.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.Client;

import java.util.UUID;

public interface ClientService {

    Flux<Client> all();

    Mono<Client> getById(UUID uuid);

    Mono<Client> getByPublicKey(String publicKey);

    Mono<Client> update(UUID uuid, Mono<Client> client);

    Mono<Client> create(Mono<Client> client);

    Mono<Client> delete(UUID uuid);
}
