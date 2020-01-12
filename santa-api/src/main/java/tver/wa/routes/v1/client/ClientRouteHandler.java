package tver.wa.routes.v1.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.Client;
import tver.wa.routes.handlers.BaseRouteHandler;
import tver.wa.services.user.UserService;

@Component
@RequiredArgsConstructor
class ClientRouteHandler extends BaseRouteHandler {

    private final UserService userService;

    Mono<ServerResponse> getById(ServerRequest r) {
        return jsonResponse(
                userService.getById(uuid(r)),
                Client.class
        );
    }

    Mono<ServerResponse> all(ServerRequest r) {
        return jsonResponse(
                userService.all(),
                Client.class
        );
    }

    Mono<ServerResponse> create(ServerRequest serverRequest) {
        return Mono.empty();
    }

    Mono<ServerResponse> update(ServerRequest serverRequest) {
        return Mono.empty();
    }

    Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return Mono.empty();
    }
}
