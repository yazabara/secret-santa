package tver.wa.services.user;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.User;

import java.util.UUID;

@Service
public interface UserService {

    Flux<User> allUsers();

    Mono<User> getUserBy(UUID uuid);
}
