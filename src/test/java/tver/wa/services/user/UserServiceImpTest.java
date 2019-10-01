package tver.wa.services.user;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.User;
import tver.wa.repositories.user.UserRepository;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class UserServiceImpTest {

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
    }

    @Test
    public void getUserBy() {
        User user = new User(UUID.randomUUID(), "Test");
        when(mockRepository.findById(user.getUuid())).thenReturn(Mono.just(user));
        //
        Mono<User> userBy = userService.getUserBy(user.getUuid());
        assertNotNull("User mono object must be present", userBy);
        assertEquals("User must be the same with user from repository", userBy.block(), user);
    }
}