package tver.wa.routes.v1.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import tver.wa.routes.handlers.BaseRouteHandler;
import tver.wa.services.event.EventService;

@Component
@RequiredArgsConstructor
public class EventRouteHandler extends BaseRouteHandler {

    private final EventService eventService;

    Mono<ServerResponse> allEvents(ServerRequest serverRequest) {
        return Mono.empty();
    }

    Mono<ServerResponse> createEvent(ServerRequest serverRequest) {
        return Mono.empty();
    }

    Mono<ServerResponse> deleteEvent(ServerRequest serverRequest) {
        return Mono.empty();
    }

    Mono<ServerResponse> updateEvent(ServerRequest serverRequest) {
        return Mono.empty();
    }

    Mono<ServerResponse> getEventById(ServerRequest serverRequest) {
        return Mono.empty();
    }
}
