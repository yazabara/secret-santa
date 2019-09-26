package tver.wa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.User;

import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private Flux<User> mockData = Flux.fromIterable(
            Arrays.asList(
                    new User(UUID.randomUUID(), "Name2"),
                    new User(UUID.randomUUID(), "Name1")
            ));

    public Flux<User> allUsers() {
        return mockData;
    }

    public Mono<User> getUserBy(UUID uuid) {
        return mockData
                .filter(user -> user.getUuid().equals(uuid))
                .next();
    }
}
