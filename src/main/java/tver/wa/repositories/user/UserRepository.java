package tver.wa.repositories.user;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.User;

import java.util.UUID;

@Repository
public interface UserRepository {

    Flux<User> findAll();

    Mono<User> findById(UUID uuid);
}
