package tver.wa.santa.services.github;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tver.wa.santa.model.github.GithubVersion;

@Service
public interface GithubDataService {

    Mono<GithubVersion> getLastGithubData();
}
