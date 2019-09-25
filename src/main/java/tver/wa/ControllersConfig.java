package tver.wa;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import tver.wa.services.GithubDataService;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
public class ControllersConfig {

    private final GithubDataService githubDataService;

    @Bean
    RouterFunction<ServerResponse> version_route() {
        return route(
                GET("/version"),
                req -> ok().body(githubDataService.getLastGithubData(), String.class)
        );
    }
}