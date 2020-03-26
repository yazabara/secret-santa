package tver.wa.events.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import tver.wa.model.secret.santa.Client;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@DataMongoTest // Mongo embedded auto configuration
public class ClientServiceImpIntegrationTest {

    @Autowired
    private ClientService clientService;

    @Test
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