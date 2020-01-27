package tver.wa.services.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.exceptions.ClientNotFoundException;
import tver.wa.model.secret.santa.Client;
import tver.wa.repositories.UUIDGenerator;
import tver.wa.repositories.client.ClientRepository;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {

    private final UUIDGenerator uuidGenerator;
    private final ClientRepository clientRepository;

    public Flux<Client> all() {
        return clientRepository.findAll();
    }

    public Mono<Client> getById(UUID uuid) {
        return clientRepository
                .findById(uuid)
                .switchIfEmpty(
                        Mono.error(new ClientNotFoundException("Client not found for uuid = " + uuid))
                );
    }

    @Override
    public Mono<Client> getByPublicKey(String publicKey) {
        return clientRepository
                .findByPublicKey(publicKey)
                .switchIfEmpty(
                        Mono.error(new ClientNotFoundException("Client not found for public key = " + publicKey))
                ).next();
    }

    public Mono<Client> update(UUID uuid, Mono<Client> client) {
        return getById(uuid)
                .map(old -> {
                    Client newClient = client.block();
                    return newClient == null ? old : old.toBuilder()
                            .name(old.getName())
                            .build();
                })
                .flatMap(entity -> {
                    log.info(String.format("Client with uuid = %s will be updated", uuid));
                    return clientRepository.save(entity);
                });
    }

    public Mono<Client> create(Mono<Client> client) {
        return client
                .map(c -> c
                        .toBuilder()
                        .uuid(uuidGenerator.generate())
                        .build()
                )
                .flatMap(entity -> {
                    log.info(String.format("New client with uuid = %s will be created", entity.getUuid()));
                    return clientRepository.save(entity);
                });
    }

    public Mono<Client> delete(UUID uuid) {
        return getById(uuid)
                .flatMap(user -> {
                    log.info(String.format("Event with uuid = %s will be deleted", uuid));
                    return clientRepository.delete(user).thenReturn(user);
                });
    }
}
