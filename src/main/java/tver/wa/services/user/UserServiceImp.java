package tver.wa.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tver.wa.exceptions.UserNotFoundException;
import tver.wa.model.secret.santa.User;
import tver.wa.repositories.user.UserRepository;

import java.util.UUID;

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
        return this.userRepository.save(user);
    }

    public Mono<User> create(User user) {
        return this.userRepository.save(user);
    }

    public Mono<User> delete(UUID uuid) {
        return this.userRepository
                .findById(uuid)
                .flatMap(
                        user -> this.userRepository.deleteById(uuid).thenReturn(user)
                );
    }
}
