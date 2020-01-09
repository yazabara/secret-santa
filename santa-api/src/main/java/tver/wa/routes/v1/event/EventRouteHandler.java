package tver.wa.routes.v1.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.Event;
import tver.wa.routes.handlers.BaseRouteHandler;
import tver.wa.services.event.EventService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventRouteHandler extends BaseRouteHandler {

    private final EventService eventService;

    Mono<ServerResponse> allEvents(ServerRequest serverRequest) {
        return jsonResponse(eventService.getAll(), Event.class);
    }

    Mono<ServerResponse> createEvent(ServerRequest serverRequest) {
        return jsonResponse(
                eventService.create(serverRequest.bodyToMono(Event.class)),
                Event.class
        );
    }

    Mono<ServerResponse> deleteEvent(ServerRequest serverRequest) {
        return jsonResponse(eventService.delete(uuid(serverRequest)), Event.class);
    }

    Mono<ServerResponse> updateEvent(ServerRequest serverRequest) {
        return jsonResponse(
                eventService.create(serverRequest.bodyToMono(Event.class)),
                Event.class,
                new EventRequestValidator(serverRequest)
        );
    }

    Mono<ServerResponse> getEventById(ServerRequest serverRequest) {
        return jsonResponse(
                eventService.getById(uuid(serverRequest)),
                Event.class
        );
    }
}
