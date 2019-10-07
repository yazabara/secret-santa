package tver.wa.routes.handlers;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

public class BaseRouteHandler {

    protected UUID uuid(ServerRequest request) {
        return UUID.fromString(request.pathVariable("uuid"));
    }

    protected static <T> Mono<ServerResponse> jsonResponse(Mono<T> monoObject, Class<T> tClass, Function<T, T> validator) {
        return defaultJsonResponse(
                monoObject.map(validator),
                tClass
        );
    }

    protected static <T> Mono<ServerResponse> jsonResponse(Mono<T> monoObject, Class<T> tClass) {
        return defaultJsonResponse(
                monoObject,
                tClass
        );
    }

    protected static <T> Mono<ServerResponse> jsonResponse(Flux<T> monoObject, Class<T> tClass, Function<T, T> validator) {
        return defaultJsonResponse(
                monoObject.map(validator),
                tClass
        );
    }

    protected static <T> Mono<ServerResponse> jsonResponse(Flux<T> monoObject, Class<T> tClass) {
        return defaultJsonResponse(
                monoObject,
                tClass
        );
    }

    private static <T> Mono<ServerResponse> defaultJsonResponse(Publisher<T> publisher, Class<T> tClass) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(publisher, tClass);
    }
}
