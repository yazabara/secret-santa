package tver.wa.web.routes.v1.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.Client;
import tver.wa.web.handlers.BaseRouteHandler;
import tver.wa.services.client.ClientService;

import static tver.wa.web.utils.ServerRequestUtils.uuid;

@Component
@RequiredArgsConstructor
class ClientRouteHandler extends BaseRouteHandler {

    private final ClientService clientService;

    Mono<ServerResponse> getById(ServerRequest r) {
        return jsonResponse(
                clientService.getById(uuid(r)),
                Client.class
        );
    }

    Mono<ServerResponse> all(ServerRequest r) {
        return jsonResponse(
                clientService.all(),
                Client.class
        );
    }

    Mono<ServerResponse> create(ServerRequest serverRequest) {
        // TODO implement
        return Mono.empty();
    }

    Mono<ServerResponse> update(ServerRequest serverRequest) {
        // TODO implement
        return Mono.empty();
    }

    Mono<ServerResponse> delete(ServerRequest serverRequest) {
        // TODO implement
        return Mono.empty();
    }
}
