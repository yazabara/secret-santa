package tver.wa.client.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import tver.wa.common.uuid.UuidGenerator;
import tver.wa.client.model.Client;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
//TODO not prod. remove
public class MockDataInitializer {

    private final UuidGenerator uuidGenerator;
    private final ClientRepository repository;

    @PostConstruct
    private void init() {
        // TODO init test users
        Client firstClient = Client.builder()
                .uuid(uuidGenerator.generate())
                .name("Yaroslav")
                .build();

        Client secondClient = Client.builder()
                .uuid(uuidGenerator.generate())
                .name("Nikita")
                .build();
        Flux
                .just(
                        firstClient,
                        secondClient
                )
                .subscribe(user -> repository
                        .findByName(user.getName())
                        .switchIfEmpty(repository.save(user))
                        .subscribe(saved -> log.debug("Test user was added: " + saved)));
    }
}
