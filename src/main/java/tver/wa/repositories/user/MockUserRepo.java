package tver.wa.repositories.user;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.exceptions.UserNotFoundException;
import tver.wa.model.secret.santa.User;

import java.util.Arrays;
import java.util.UUID;

@Component
public class MockUserRepo implements UserRepository {
    // TODO remove after using real repo

    private Flux<User> mockData = Flux.fromIterable(
            Arrays.asList(
                    new User(UUID.randomUUID(), "Name2"),
                    new User(UUID.randomUUID(), "Name1")
            ));


    @Override
    public Flux<User> findAll() {
        return mockData;
    }

    @Override
    public Mono<User> findById(UUID uuid) {
        return mockData
                .filter(user -> user.getUuid().equals(uuid))
                .next()
                .switchIfEmpty(Mono.error(new UserNotFoundException("User with this uuid not found")));
    }
}
