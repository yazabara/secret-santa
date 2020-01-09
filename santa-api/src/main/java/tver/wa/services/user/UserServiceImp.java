package tver.wa.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.exceptions.UserNotFoundException;
import tver.wa.model.secret.santa.User;
import tver.wa.repositories.user.UserRepository;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public Flux<User> allUsers() {
        return userRepository.findAll();
    }

    public Mono<User> getUserBy(UUID uuid) {
        return userRepository
                .findById(uuid)
                .switchIfEmpty(
                        Mono.error(new UserNotFoundException("User not found for uuid = " + uuid))
                );
    }

    public Mono<User> update(User user) {
        return getUserBy(user.getUuid())
                .flatMap(u -> userRepository.save(user));
    }

    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> delete(UUID uuid) {
        return getUserBy(uuid)
                .flatMap(user -> {
                    log.info(String.format("Event with uuid = %s will be deleted", uuid));
                    return userRepository.delete(user).thenReturn(user);
                });
    }
}
