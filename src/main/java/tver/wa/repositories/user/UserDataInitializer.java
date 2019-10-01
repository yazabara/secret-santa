package tver.wa.repositories.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import tver.wa.model.secret.santa.User;

import javax.annotation.PostConstruct;
import java.util.Arrays;
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
                .fromIterable(
                        Arrays.asList(
                                new User(UUID.randomUUID(), "Yaroslav"),
                                new User(UUID.randomUUID(), "Nikita")
                        )
                )
                .subscribe(user -> {
                    if (repository.findByName(user.getName()).block() == null) {
                        repository.save(user);
                        log.debug("Test user was added: " + user);
                    }
                });
    }
}
