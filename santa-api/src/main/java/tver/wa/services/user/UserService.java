package tver.wa.services.user;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.User;

import java.util.UUID;

public interface UserService {

    Flux<User> allUsers();

    Mono<User> getUserBy(UUID uuid);

    Mono<User> update(User user);

    Mono<User> create(User user);

    Mono<User> delete(UUID uuid);
}
