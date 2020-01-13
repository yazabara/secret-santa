package tver.wa.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactor.core.publisher.Mono;
import tver.wa.model.github.GithubVersion;
import tver.wa.services.github.GithubDataServiceImp;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GithubDataServiceImpTest {

    @Autowired
    private GithubDataServiceImp githubDataService;

    @Test
    public void getLastGithubDataTest() {
        Mono<GithubVersion> data = githubDataService.getLastGithubData();
        assertNotNull("Github version must be present", data);
    }
}
