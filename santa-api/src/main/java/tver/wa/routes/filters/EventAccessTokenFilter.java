package tver.wa.routes.filters;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static tver.wa.routes.utils.ServerRequestUtils.publicKey;

@Component
public class EventAccessTokenFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        exchange.getResponse()
//                .getHeaders()
        publicKey(exchange.getRequest())
        return null;
    }
}
