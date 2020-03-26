package tver.wa.client.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.client.model.Client;

import java.util.UUID;

public interface ClientService {

    Flux<Client> all();

    Mono<Client> getById(UUID uuid);

    Mono<Client> update(UUID uuid, Mono<Client> client);

    Mono<Client> create(Mono<Client> client);

    Mono<Client> delete(UUID uuid);
}
