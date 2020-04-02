package tver.wa.client.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import tver.wa.client.clients.EventApiClient;
import tver.wa.client.model.Client;
import tver.wa.client.services.ClientService;
import tver.wa.common.model.EventDto;
import tver.wa.common.web.BaseRouteHandler;

import static tver.wa.common.web.ServerRequestUtils.uuid;


@Component
@RequiredArgsConstructor
class ClientRouteHandler extends BaseRouteHandler {

    private final ClientService clientService;
    private final EventApiClient eventApiClient;

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
        return jsonResponse(
                eventApiClient.createEvent(
                        serverRequest.bodyToMono(EventDto.class).block()
                ),
                EventDto.class
        );
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
