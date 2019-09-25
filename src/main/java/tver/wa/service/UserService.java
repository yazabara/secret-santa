package tver.wa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tver.wa.exceptions.UserNotFoundException;
import tver.wa.model.secret.santa.User;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService {

    private List<User> mock_data = new ArrayList<>();

    @PostConstruct
    public void init() {
        mock_data = Arrays.asList(new User(UUID.randomUUID(), "Name2"), new User(UUID.randomUUID(), "Name1"));
    }

    public Collection<User> allUsers() {
        return mock_data;
    }

    public User getUserBy(UUID uuid) throws UserNotFoundException {
        return mock_data.stream().filter(user -> user.getUuid().equals(uuid)).findFirst().orElseThrow(new Supplier<RuntimeException>() {
            @Override
            public RuntimeException get() {
                throw new UserNotFoundException("User with uuid = " + uuid + " not found");
            }
        });
    }
}
