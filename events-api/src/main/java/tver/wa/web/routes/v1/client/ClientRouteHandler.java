package tver.wa.web.routes.v1.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.Client;
import tver.wa.services.client.ClientService;
import tver.wa.web.handlers.BaseRouteHandler;

import static tver.wa.web.utils.ServerRequestUtils.*;

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
        return bodyFromResponse(serverRequest, Client.class)
                .flatMap(clientService::create)
                .flatMap(BaseRouteHandler::jsonResponse);
    }

    Mono<ServerResponse> update(ServerRequest serverRequest) {
        return bodyFromResponse(serverRequest, Client.class)
                .flatMap(clientService::update)
                .flatMap(BaseRouteHandler::jsonResponse);
    }

    Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return uuidMono(serverRequest)
                .flatMap(clientService::delete)
                .flatMap(BaseRouteHandler::jsonResponse);
    }
}
