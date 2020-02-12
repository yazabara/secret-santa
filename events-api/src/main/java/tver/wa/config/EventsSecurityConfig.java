package tver.wa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import tver.wa.services.security.ClientRoles;

@Slf4j
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class EventsSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChainSecure(ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                .pathMatchers("/v1/events/**").hasRole(ClientRoles.CLIENT.toString())
                .pathMatchers("/v1/clients/**").hasRole(ClientRoles.ADMIN.toString())
                .anyExchange().authenticated()
                .and()
                .httpBasic().and()
                .csrf().disable()
                .build();
    }
}
