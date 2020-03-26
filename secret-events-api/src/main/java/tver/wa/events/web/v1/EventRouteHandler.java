package tver.wa.events.web.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import tver.wa.events.model.Event;
import tver.wa.events.services.EventService;
import tver.wa.web.BaseRouteHandler;
import tver.wa.web.ServerRequestUtils;

import javax.validation.Valid;

import static tver.wa.web.ServerRequestUtils.uuid;

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
                // TODO
                eventService.create(null, serverRequest.bodyToMono(Event.class)),
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
