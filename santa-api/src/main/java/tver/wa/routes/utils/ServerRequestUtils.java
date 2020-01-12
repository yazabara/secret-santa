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

    public static String header(ServerHttpRequest request, String name) {
        return request.getHeaders().getFirst(name);
    }

    public static String secretKey(ServerHttpRequest request) {
        return header(request, "Secret-Key");
    }

    public static String publicKey(ServerHttpRequest request) {
        return header(request, "Public-Key");
    }
}
