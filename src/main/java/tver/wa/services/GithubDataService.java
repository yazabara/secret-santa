package tver.wa.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import tver.wa.model.GithubCommitsData;

@Service
@RequiredArgsConstructor
public class GithubDataService {

  private final WebClient webGithubClient;
  @Value("${github.url}")
  private String url;

  public Mono<GithubCommitsData> getLastGithubData() {
    return webGithubClient
        .get()
        .uri("/repos/yazabara/secret-santa/commits/master")
        .retrieve()
        .bodyToMono(GithubCommitsData.class);
  }

}
