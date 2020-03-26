package tver.wa.events.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import tver.wa.events.services.github.GithubDataService;
import tver.wa.events.model.github.GithubVersion;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class GithubDataServiceImpTest {

    @Autowired
    private GithubDataService githubDataService;

    @Test
    public void getLastGithubDataTest() {
        Mono<GithubVersion> data = githubDataService.getLastGithubData();
        assertNotNull("Github version must be present", data);
    }
}
