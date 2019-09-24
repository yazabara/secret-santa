package tver.wa.services;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GithubDataService {

  private final WebClient webGithubClient;
  @Value("${github.url}")
  private String url;

//  public Mono<String> getLastGithubData() {
//    return webGithubClient.get()
//        .uri("/repos/yazabara/secret-santa/commits/master")
//        .retrieve()
//        .bodyToMono(String.class);
//  }

  public Mono<String> getLastGithubData() {
    HttpClient client = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet(url + "/repos/yazabara/secret-santa/commits/master");
    try {
      HttpResponse response = client.execute(request);
      return Mono.just(EntityUtils.toString(response.getEntity()));
    } catch (IOException ignored) {
    }
    return Mono.empty();
  }
}
