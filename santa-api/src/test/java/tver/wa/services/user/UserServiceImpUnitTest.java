package tver.wa.services.user;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import tver.wa.model.secret.santa.User;
import tver.wa.repositories.user.UserRepository;

import java.util.Arrays;
import java.util.UUID;
import java.util.function.Predicate;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class UserServiceImpUnitTest {

    @Mock
    private UserRepository mockRepository;

    @InjectMocks
    private UserServiceImp userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mockInjectionWorks() {
        assertNotNull(userService);
    }

    @Test
    public void allUsers() {
        User user = User.builder().uuid(UUID.randomUUID()).name("Test1").build();
        User user2 = User.builder().uuid(UUID.randomUUID()).name("Test2").build();
        when(mockRepository.findAll()).thenReturn(Flux.fromIterable(Arrays.asList(user, user2)));

        Flux<User> usersList = userService.allUsers();
        Predicate<User> check = dbUser -> usersList.any(savedUser -> savedUser.equals(dbUser)).block();
        StepVerifier
                .create(usersList)
                .expectNextMatches(check)
                .expectNextMatches(check)
                .verifyComplete();
    }

    @Test
    public void getUserBy() {
        User user = User.builder().uuid(UUID.randomUUID()).name("Test1").build();
        when(mockRepository.findById(user.getUuid())).thenReturn(Mono.just(user));
        //
        Mono<User> userBy = userService.getUserBy(user.getUuid());
        StepVerifier
                .create(userBy)
                .expectNextMatches(userFromDB -> userFromDB.equals(user))
                .verifyComplete();
    }
}