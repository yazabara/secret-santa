package tver.wa.web.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@UtilityClass
public class ServerRequestUtils {

    public static UUID uuid(ServerRequest request) {
        return UUID.fromString(request.pathVariable("uuid"));
    }

    private static Optional<String> header(ServerRequest request, String name) {
        List<String> header = request.headers().header(name);
        return CollectionUtils.isNotEmpty(header) && StringUtils.isNotBlank(header.get(0)) ?
                Optional.of(header.get(0)) : Optional.empty();
    }

    private static Optional<String> header(ServerHttpRequest request, String name) {
        List<String> header = request.getHeaders().get(name);
        return CollectionUtils.isNotEmpty(header) && StringUtils.isNotBlank(header.get(0)) ?
                Optional.of(header.get(0)) : Optional.empty();
    }

    public static Optional<String> secretKey(ServerRequest request) {
        return header(request, "Secret-Key");
    }

    public static Optional<String> publicKey(ServerRequest request) {
        return header(request, "Public-Key");
    }

    public static Optional<String> secretKey(ServerHttpRequest request) {
        return header(request, "Secret-Key");
    }

    public static Optional<String> publicKey(ServerHttpRequest request) {
        return header(request, "Public-Key");
    }
}
