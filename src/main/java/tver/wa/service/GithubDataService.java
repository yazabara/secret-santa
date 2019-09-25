package tver.wa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tver.wa.model.github.GithubCommitsData;

@Service
@RequiredArgsConstructor
public class GithubDataService {

    private final WebClient webGithubClient;

    public Mono<GithubCommitsData> getLastGithubData() {
        return webGithubClient
                .get()
                .uri("/repos/yazabara/secret-santa/commits/master")
                .retrieve()
                .bodyToMono(GithubCommitsData.class);
    }
}
