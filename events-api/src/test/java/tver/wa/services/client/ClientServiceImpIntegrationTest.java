package tver.wa.services.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import tver.wa.exceptions.ClientNotFoundException;
import tver.wa.model.secret.santa.Client;
import tver.wa.repositories.UUIDGenerator;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class) // Junit4 runner
@DataMongoTest // Mongo embedded auto configuration
@Import({ClientServiceImp.class, UUIDGenerator.class}) // enable bean injection for beans in {}
public class ClientServiceImpIntegrationTest {

    @Autowired
    private ClientService clientService;

    @Test(expected = ClientNotFoundException.class)
    public void getUserByMustWorkCorrect() {
        Mono<Client> userBy = clientService.getById(UUID.randomUUID());
        assertNotNull("User mono object must be present", userBy);
        userBy.block(); //expected UserNotFoundException
    }

    @Test
    public void saveMustWorkCorrect() {
        Client newClient = Client.builder().uuid(UUID.randomUUID()).name("Test user").build();
        Mono<Client> testUser = clientService.create(Mono.just(newClient));
        StepVerifier
                .create(testUser)
                .expectNextMatches(saved -> saved.getName().equals(newClient.getName()))
                .verifyComplete();
    }
}