package tver.wa.web.routes.v1.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.Event;
import tver.wa.services.event.EventService;
import tver.wa.web.handlers.BaseRouteHandler;
import tver.wa.web.utils.ServerRequestUtils;

import javax.validation.Valid;

import static tver.wa.web.utils.ServerRequestUtils.uuid;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventRouteHandler extends BaseRouteHandler {

    private final EventService eventService;

    Mono<ServerResponse> allEvents(ServerRequest serverRequest) {
        return jsonResponse(eventService.getAll(), Event.class);
    }

    Mono<ServerResponse> createEvent(@Valid ServerRequest serverRequest) {
        return jsonResponse(
                eventService.create(ServerRequestUtils.publicKey(serverRequest).get(), serverRequest.bodyToMono(Event.class)),
                Event.class
        );
    }

    Mono<ServerResponse> deleteEvent(ServerRequest serverRequest) {
        return jsonResponse(eventService.delete(uuid(serverRequest)), Event.class);
    }

    Mono<ServerResponse> updateEvent(ServerRequest serverRequest) {
        return jsonResponse(
                eventService.update(uuid(serverRequest), serverRequest.bodyToMono(Event.class)),
                Event.class
        );
    }

    Mono<ServerResponse> getEventById(@Valid ServerRequest serverRequest) {
        return jsonResponse(
                eventService.getById(uuid(serverRequest)),
                Event.class
        );
    }
}
