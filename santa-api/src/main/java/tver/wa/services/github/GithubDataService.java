package tver.wa.services.github;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tver.wa.model.github.GithubVersion;

@Service
public interface GithubDataService {

    Mono<GithubVersion> getLastGithubData();
}
