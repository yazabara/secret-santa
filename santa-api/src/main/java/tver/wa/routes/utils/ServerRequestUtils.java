package tver.wa.routes.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.UUID;

@UtilityClass
public class ServerRequestUtils {

    public static UUID uuid(ServerRequest request) {
        return UUID.fromString(request.pathVariable("uuid"));
    }

    public static String header(ServerRequest request, String name) {
        return serverRequest.headers().asHttpHeaders().getFirst(name)
    }

    private static String secretKey(ServerRequest request) {
        return header(request, "Secret-Key")
    }

    private static String publicKey(ServerHttpRequest request) {
        return header(request, "Public-Key")
    }
}
