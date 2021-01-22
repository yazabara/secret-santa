package tver.wa.web.handlers;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BaseRouteHandler {

    protected static <T> Mono<ServerResponse> jsonResponse(Mono<T> monoObject, Class<T> tClass) {
        return defaultJsonResponse(
                monoObject,
                tClass
        );
    }

    protected static <T> Mono<ServerResponse> jsonResponse(Flux<T> monoObject, Class<T> tClass) {
        return defaultJsonResponse(
                monoObject,
                tClass
        );
    }

    protected static <T> Mono<ServerResponse> jsonResponse(T monoObject) {
        return defaultJsonResponse(
                monoObject
        );
    }

    private static <T> Mono<ServerResponse> defaultJsonResponse(Publisher<T> publisher, Class<T> tClass) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(publisher, tClass);
    }

    private static <T> Mono<ServerResponse> defaultJsonResponse(T body) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(body);
    }
}
