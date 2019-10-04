package tver.wa.routes;

import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;
import tver.wa.exceptions.IncorrectTokenException;
import tver.wa.exceptions.UserNotFoundException;


@Component
@Order(-1)
public class GlobalErrorHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof UserNotFoundException || ex instanceof IllegalArgumentException) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
            return exchange
                    .getResponse()
                    .writeWith(
                            createResponseBody(
                                    exchange.getResponse().bufferFactory(),
                                    ex.getLocalizedMessage()
                            )
                    );
        }
        if (ex instanceof ResponseStatusException) {
            exchange.getResponse().setStatusCode(((ResponseStatusException) ex).getStatus());
        }
        if (ex instanceof IncorrectTokenException) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
            return exchange
                    .getResponse()
                    .writeWith(
                            createResponseBody(
                                    exchange.getResponse().bufferFactory(),
                                    ex.getLocalizedMessage()
                            )
                    );
        }
        return exchange.getResponse().setComplete();
    }

    private Mono<DataBuffer> createResponseBody(DataBufferFactory factory, String message) {
        return Mono.just(factory.allocateBuffer().write(message.getBytes()));
    }
}
