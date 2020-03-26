package tver.wa.events.services.github;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tver.wa.events.model.github.GithubVersion;

@Service
public interface GithubDataService {

    Mono<GithubVersion> getLastGithubData();
}
