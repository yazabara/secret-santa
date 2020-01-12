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
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
//TODO not prod. remove
public class MockDataInitializer {

    private final ClientRepository repository;
    private final EventRepository eventRepository;

    @PostConstruct
    private void init() {
        // TODO init test users
        Client firstClient = Client.builder()
                .uuid(UUID.fromString("737caee6-c848-40f4-9191-6bbdd161d302"))
                .name("Yaroslav's Client")
                .secretKey("secret")
                .publicKey("public")
                .build();

        Client secondClient = Client.builder()
                .uuid(UUID.fromString("afca25b2-5b84-45dd-ada0-41585e79aad7"))
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

        eventRepository.deleteAll();
        Mono
                .just(Event
                        .builder()
                        .creator(firstClient.getUuid())
                        .start(new Date())
                        .end(new Date())
                        .description("Event description")
                        .uuid(UUID.fromString("afca25b2-5b84-45dd-ada0-41585e79aad8"))
                        .participants(Arrays.asList(firstClient.getUuid(), secondClient.getUuid()))
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
