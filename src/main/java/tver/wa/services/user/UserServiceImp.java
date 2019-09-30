package tver.wa.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
        return userRepository.findById(uuid);
    }
}
