package tver.wa.routes.v1.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import tver.wa.model.secret.santa.User;
import tver.wa.routes.handlers.BaseRouteHandler;
import tver.wa.services.user.UserService;

@Component
@RequiredArgsConstructor
class UserRouteHandler extends BaseRouteHandler {

    private final UserService userService;

    Mono<ServerResponse> getById(ServerRequest r) {
        return jsonResponse(
                userService.getUserBy(uuid(r)),
                User.class
        );
    }

    Mono<ServerResponse> allUsers(ServerRequest r) {
        return jsonResponse(
                userService.allUsers(),
                User.class
        );
    }

    Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return Mono.empty();
    }

    Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        return Mono.empty();
    }

    Mono<ServerResponse> deleteUser(ServerRequest serverRequest) {
        return Mono.empty();
    }
}
