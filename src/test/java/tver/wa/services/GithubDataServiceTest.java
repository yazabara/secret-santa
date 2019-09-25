package tver.wa.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import reactor.core.publisher.Mono;
import tver.wa.model.GithubCommitsData;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GithubDataServiceTest {

  @Autowired
  private GithubDataService githubDataService;

  @Test
  public void getLastGithubDataTest() {
    Mono<GithubCommitsData> data = githubDataService.getLastGithubData();
    System.out.println(data.block());
  }


}
