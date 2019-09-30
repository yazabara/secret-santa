package tver.wa.routes.handlers;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class BaseRouteHandler {

    protected UUID uuid(ServerRequest request) {
        return UUID.fromString(request.pathVariable("uuid"));
    }

    protected static <T> Mono<ServerResponse> defaultJsonResponse(Publisher<T> publisher, Class<T> tClass) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(publisher, tClass);
    }
}
