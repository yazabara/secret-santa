package tver.wa.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import tver.wa.model.GithubCommitsData;

@Service
@RequiredArgsConstructor
public class GithubDataService {

  private final WebClient webGithubClient;

  public Mono<String> getLastGithubData() {
    return webGithubClient.get()
        .uri("/repos/yazabara/secret-santa/commits/master")
        .retrieve()
        .bodyToMono(String.class);
  }

//  public Mono<String> getLastGithubData() {
//    HttpClient client = HttpClientBuilder.create().build();
//    HttpGet request = new HttpGet(url);
//
//  }
}
