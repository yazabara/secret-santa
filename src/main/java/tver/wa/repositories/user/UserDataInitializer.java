package tver.wa.repositories.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import tver.wa.model.secret.santa.User;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDataInitializer {

    private final UserRepository repository;

    @PostConstruct
    private void init() {
        // TODO init test users
        Flux
                .just(
                        new User(UUID.fromString("737caee6-c848-40f4-9191-6bbdd161d302"), "Yaroslav"),
                        new User(UUID.fromString("afca25b2-5b84-45dd-ada0-41585e79aad7"), "Nikita")
                )
                .subscribe(user -> {
                    repository
                            .findById(user.getUuid())
                            .switchIfEmpty(repository.save(user))
                            .subscribe(saved -> log.debug("Test user was added: " + saved));
                });
    }
}
