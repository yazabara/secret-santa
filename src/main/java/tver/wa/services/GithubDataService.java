package tver.wa.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GithubDataService {

    private final WebClient webGithubClient;

    public Mono<String> getLastGithubData() {
        return webGithubClient
                .get()
                .uri("/repos/yazabara/secret-santa/commits/master")
                .retrieve()
                .bodyToMono(String.class);
    }
}
