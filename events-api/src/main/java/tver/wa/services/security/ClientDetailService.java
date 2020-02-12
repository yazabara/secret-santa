package tver.wa.services.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tver.wa.services.client.ClientService;


@Service
@RequiredArgsConstructor
public class ClientDetailService implements ReactiveUserDetailsService {

    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return clientService
                .getByPublicKey(username)
                .map(client -> User
                        .builder()
                        .username(client.getPublicKey())
                        .password(passwordEncoder.encode(client.getSecretKey()))
                        .roles(client.getRoles().stream().map(Enum::toString).toArray(String[]::new))
                        .build()
                );
    }
}
