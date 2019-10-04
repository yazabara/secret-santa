package tver.wa.repositories.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.Event;
import tver.wa.model.secret.santa.User;
import tver.wa.repositories.event.EventRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDataInitializer {

    private final UserRepository repository;
    private final EventRepository eventRepository;

    @PostConstruct
    private void init() {
        // TODO init test users
        User firstUser = new User(UUID.fromString("737caee6-c848-40f4-9191-6bbdd161d302"), "Yaroslav");
        User secondUser = new User(UUID.fromString("afca25b2-5b84-45dd-ada0-41585e79aad7"), "Nikita");
        Flux
                .just(
                        firstUser,
                        secondUser
                )
                .subscribe(user -> {
                    repository
                            .findById(user.getUuid())
                            .switchIfEmpty(repository.save(user))
                            .subscribe(saved -> log.debug("Test user was added: " + saved));
                });
        Mono
                .just(new Event(
                        UUID.fromString("afca25b2-5b84-45dd-ada0-41585e79aad8"),
                        firstUser,
                        "Test token",
                        Arrays.asList(firstUser, secondUser)
                ))
                .subscribe(
                event -> eventRepository
                        .findById(event.getUuid())
                        .switchIfEmpty(eventRepository.save(event))
                        .subscribe(savedEvent -> log.debug("Init test event data" + event))
        );
    }
}
