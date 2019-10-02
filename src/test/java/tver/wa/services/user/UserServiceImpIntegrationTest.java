package tver.wa.services.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import tver.wa.exceptions.UserNotFoundException;
import tver.wa.model.secret.santa.User;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class) // Junit4 runner
@DataMongoTest // Mongo embedded auto configuration
@Import({UserServiceImp.class}) // enable bean injection for beans in {}
@ActiveProfiles("integration") // active profiler
// the same configuration:
//@RunWith(SpringRunner.class) // Junit4 runner
//@SpringBootTest // test bean injection
//@AutoConfigureDataMongo  // Mongo embedded auto configuration
//@ActiveProfiles("integration") // active profiler
public class UserServiceImpIntegrationTest {

    @Autowired
    private UserService userService;

    @Test(expected = UserNotFoundException.class)
    public void getUserByMustWorkCorrect() {
        Mono<User> userBy = userService.getUserBy(UUID.randomUUID());
        assertNotNull("User mono object must be present", userBy);
        userBy.block(); //expected UserNotFoundException
    }

    @Test
    public void saveMustWorkCorrect() {
        User newUser = new User(UUID.randomUUID(), "Test user");
        Mono<User> testUser = userService.create(newUser);
        StepVerifier
                .create(testUser)
                .expectNextMatches(saved -> saved.getName().equals(newUser.getName()))
                .verifyComplete();
    }
}