package tver.wa.web.filters;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import tver.wa.exceptions.ClientNotFoundException;
import tver.wa.services.client.ClientService;
import tver.wa.web.utils.ServerRequestUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientAccessTokenFilter implements WebFilter {

    private final ClientService clientService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        final Optional<String> publicKey = ServerRequestUtils.publicKey(exchange.getRequest());
        final Optional<String> secretKey = ServerRequestUtils.secretKey(exchange.getRequest());

        if (!publicKey.isPresent() || !secretKey.isPresent()) {
            throw new ClientNotFoundException("Client public/secret key are empty.");
        }

        return clientService
                .getByPublicKey(publicKey.get())
                .filter(client -> StringUtils.equals(client.getSecretKey(), secretKey.get()))
                .switchIfEmpty(
                        Mono.error(new ClientNotFoundException(
                                String.format("Client with public key: %s has wrong secret key: %s", publicKey.get(), secretKey.get()))
                        )
                )
                .flatMap(data -> chain.filter(exchange));
    }
}
