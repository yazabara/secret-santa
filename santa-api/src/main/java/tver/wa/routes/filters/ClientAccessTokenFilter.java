package tver.wa.routes.filters;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import tver.wa.exceptions.ClientNotFoundException;
import tver.wa.services.client.ClientService;

import static tver.wa.routes.utils.ServerRequestUtils.publicKey;
import static tver.wa.routes.utils.ServerRequestUtils.secretKey;


@Component
@RequiredArgsConstructor
public class ClientAccessTokenFilter implements WebFilter {

    private final ClientService clientService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        final String publicKey = publicKey(exchange.getRequest());
        if (StringUtils.isNotBlank(publicKey)) {
            throw new ClientNotFoundException("Client public key is empty.");
        }
        final String secretKey = secretKey(exchange.getRequest());
        return clientService
                .getByPublicKey(publicKey)
                .filter(client -> StringUtils.equals(client.getSecretKey(), secretKey))
                .switchIfEmpty(
                        Mono.error(new ClientNotFoundException("Client " + publicKey + " has wrong secret key"))
                ).then();
    }
}
