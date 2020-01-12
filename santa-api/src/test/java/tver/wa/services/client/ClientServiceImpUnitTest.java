package tver.wa.services.client;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import tver.wa.model.secret.santa.Client;
import tver.wa.repositories.client.ClientRepository;

import java.util.Arrays;
import java.util.UUID;
import java.util.function.Predicate;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class ClientServiceImpUnitTest {

    @Mock
    private ClientRepository mockRepository;

    @InjectMocks
    private ClientServiceImp userService;

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
        Client client = Client.builder().uuid(UUID.randomUUID()).name("Test1").build();
        Client client2 = Client.builder().uuid(UUID.randomUUID()).name("Test2").build();
        when(mockRepository.findAll()).thenReturn(Flux.fromIterable(Arrays.asList(client, client2)));

        Flux<Client> usersList = userService.all();
        Predicate<Client> check = dbUser -> usersList.any(savedUser -> savedUser.equals(dbUser)).block();
        StepVerifier
                .create(usersList)
                .expectNextMatches(check)
                .expectNextMatches(check)
                .verifyComplete();
    }

    @Test
    public void getUserBy() {
        Client client = Client.builder().uuid(UUID.randomUUID()).name("Test1").build();
        when(mockRepository.findById(client.getUuid())).thenReturn(Mono.just(client));
        //
        Mono<Client> userBy = userService.getById(client.getUuid());
        StepVerifier
                .create(userBy)
                .expectNextMatches(userFromDB -> userFromDB.equals(client))
                .verifyComplete();
    }
}