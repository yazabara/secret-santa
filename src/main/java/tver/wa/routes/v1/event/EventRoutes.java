package tver.wa.routes.v1.event;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class EventRoutes {

    private final EventRouteHandler handler;

    @Bean
    RouterFunction<ServerResponse> eventApiV1() {
        return route(GET("/v1/events"), handler::allEvents)
                .andRoute(GET("/v1/events/{uuid}"), handler::getEventById)
                .andRoute(DELETE("/v1/events/{uuid}"), handler::deleteEvent)
                .andRoute(PUT("/v1/events/{uuid}"), handler::updateEvent)
                .andRoute(POST("/v1/events/{uuid}"), handler::createEvent);
    }
}
