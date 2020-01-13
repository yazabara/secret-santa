package tver.wa.services.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.exceptions.ClientNotFoundException;
import tver.wa.model.secret.santa.Client;
import tver.wa.repositories.client.ClientRepository;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {

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

    public Mono<Client> update(Client client) {
        return getById(client.getUuid())
                .flatMap(u -> clientRepository.save(client));
    }

    public Mono<Client> create(Client client) {
        return clientRepository.save(client);
    }

    public Mono<Client> delete(UUID uuid) {
        return getById(uuid)
                .flatMap(user -> {
                    log.info(String.format("Event with uuid = %s will be deleted", uuid));
                    return clientRepository.delete(user).thenReturn(user);
                });
    }
}
