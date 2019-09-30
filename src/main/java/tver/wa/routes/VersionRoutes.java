package tver.wa.routes;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import tver.wa.routes.handlers.VersionHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class VersionRoutes {

    private final VersionHandler versionHandler;

    @Bean
    RouterFunction<ServerResponse> versionApi() {
        return route(GET("/version"), versionHandler::getVersion);
    }
}
