package tver.wa.web.routes.v1.client;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class ClientRoutes {

    private final ClientRouteHandler handler;

    @Bean
    RouterFunction<ServerResponse> userApiV1() {
        return route(GET("/v1/clients"), handler::all)
                .andRoute(GET("/v1/clients/{uuid}"), handler::getById)
                .andRoute(DELETE("/v1/clients/{uuid}"), handler::delete)
                .andRoute(PUT("/v1/clients/{uuid}"), handler::update)
                .andRoute(POST("/v1/clients/{uuid}"), handler::create);
    }
}