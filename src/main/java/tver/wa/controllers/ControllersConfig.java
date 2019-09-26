package tver.wa.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import tver.wa.model.github.GithubCommitsData;
import tver.wa.model.secret.santa.User;
import tver.wa.service.GithubDataService;
import tver.wa.service.UserService;

import java.util.UUID;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
public class ControllersConfig {

    private final GithubDataService githubDataService;
    private final UserService userService;

    @Bean
    RouterFunction<ServerResponse> routes() {
        return route(
                GET("/version"),
                req -> ok().body(
                        githubDataService.getLastGithubData(),
                        GithubCommitsData.class
                ).onErrorResume(throwable -> ServerResponse.notFound().build())
        ).andRoute(
                GET("/user/"),
                request -> ok().body(
                        userService.allUsers(),
                        User.class
                )
        ).andRoute(
                GET("/user/{uuid}"),
                request -> Mono
                        .justOrEmpty(request.pathVariable("uuid"))
                        .map(s -> userService.getUserBy(UUID.fromString(s)))
                        .flatMap(user -> user
                                // correct responses
                                .flatMap(u -> ok().body(user, User.class))
                                .switchIfEmpty(ServerResponse.notFound().build())
                        )
                        .onErrorResume(throwable -> ServerResponse.badRequest().build())

        );
    }
}