package tver.wa.services.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import tver.wa.model.secret.santa.User;

import java.util.UUID;

@DataMongoTest(includeFilters = @ComponentScan.Filter(Service.class))
@Import(UserService.class)
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        User newUser = new User(UUID.randomUUID(), "Test user");
        Mono<User> testUser = this.userService.create(newUser);
        StepVerifier
                .create(testUser)
                .expectNextMatches(saved -> saved.getName().equals(newUser.getName()))
                .verifyComplete();
    }
}
