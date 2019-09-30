package tver.wa.routes.v1.user;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class UserRoutes {

    private final UserRouteHandler handler;

    @Bean
    RouterFunction<ServerResponse> userApiV1() {
        return route(GET("/v1/users"), handler::allUsers)
                .andRoute(GET("/v1/users/{uuid}"), handler::getById)
                .andRoute(DELETE("/v1/users/{uuid}"), handler::getById)
                .andRoute(PUT("/v1/users/{uuid}"), handler::updateUser)
                .andRoute(POST("/v1/users/{uuid}"), handler::createUser);
    }
}