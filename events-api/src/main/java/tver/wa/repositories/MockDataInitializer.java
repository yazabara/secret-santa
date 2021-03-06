package tver.wa.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.Client;
import tver.wa.model.secret.santa.Event;
import tver.wa.repositories.client.ClientRepository;
import tver.wa.repositories.event.EventRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
//TODO not prod. remove
public class MockDataInitializer {

    private final UUIDGenerator uuidGenerator;
    private final ClientRepository repository;
    private final EventRepository eventRepository;

    @PostConstruct
    private void init() {
        // TODO init test users
        Client firstClient = Client.builder()
                .uuid(uuidGenerator.generate())
                .name("Yaroslav's Client")
                .secretKey("secret")
                .publicKey("public")
                .build();

        Client secondClient = Client.builder()
                .uuid(uuidGenerator.generate())
                .name("Nikita's Client")
                .build();
        Flux
                .just(
                        firstClient,
                        secondClient
                )
                .subscribe(user -> repository
                        .findById(user.getUuid())
                        .switchIfEmpty(repository.save(user))
                        .subscribe(saved -> log.debug("Test user was added: " + saved)));

        Mono
                .just(Event
                        .builder()
                        .uuid(uuidGenerator.generate())
                        .creator(firstClient.getUuid())
                        .start(new Date())
                        .end(new Date())
                        .description("Event description")
                        .participants(Arrays.asList("Yaroslav's Client", "Nikita's Client"))
                        .build()
                )
                .subscribe(
                        event -> eventRepository
                                .findById(event.getUuid())
                                .switchIfEmpty(eventRepository.save(event))
                                .subscribe(savedEvent -> log.debug("Init test event data" + event))
                );
    }
}
