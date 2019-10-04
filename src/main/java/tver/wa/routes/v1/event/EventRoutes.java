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
        String basicUrl = "/v1/events";
        return route(GET(basicUrl), handler::allEvents)
                .andRoute(GET(basicUrl.concat("/{uuid}")), handler::getEventById)
                .andRoute(DELETE(basicUrl.concat("/{uuid}")), handler::deleteEvent)
                .andRoute(PUT(basicUrl), handler::updateEvent)
                .andRoute(POST(basicUrl), handler::createEvent);
    }
}
