package tver.wa.events.services.github;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tver.wa.events.model.github.GithubCommitsData;
import tver.wa.events.model.github.GithubVersion;

@Service
@RequiredArgsConstructor
public class GithubDataServiceImp implements GithubDataService {

    private final WebClient webGithubClient;
    private final String gitRepositoryLink;

    public Mono<GithubVersion> getLastGithubData() {
        return Mono.from(
                webGithubClient
                        .get()
                        .uri(gitRepositoryLink)
                        .retrieve()
                        .bodyToMono(GithubCommitsData.class)
        );
    }
}
