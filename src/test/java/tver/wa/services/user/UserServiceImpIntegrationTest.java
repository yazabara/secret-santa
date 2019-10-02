package tver.wa.services.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import tver.wa.exceptions.UserNotFoundException;
import tver.wa.model.secret.santa.User;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class) // Junit4 runner
@DataMongoTest
@Import({UserServiceImp.class})
@ActiveProfiles("integration")
public class UserServiceImpIntegrationTest {

    @Autowired
    private UserService userService;

    @Test(expected = UserNotFoundException.class)
    public void getUserBy() {
        Mono<User> userBy = userService.getUserBy(UUID.randomUUID());
        assertNotNull("User mono object must be present", userBy);
        userBy.block(); //expected UserNotFoundException
    }
}