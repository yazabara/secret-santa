package tver.wa.web.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import tver.wa.model.github.GithubVersion;
import tver.wa.services.github.GithubDataService;

@Component
@RequiredArgsConstructor
public class VersionHandler extends BaseRouteHandler {

    private final GithubDataService githubDataService;

    public Mono<ServerResponse> getVersion(ServerRequest r) {
        return jsonResponse(
                githubDataService.getLastGithubData(),
                GithubVersion.class
        );
    }
}
