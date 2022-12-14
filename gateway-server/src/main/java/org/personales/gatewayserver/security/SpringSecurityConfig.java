package org.personales.gatewayserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SpringSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                .pathMatchers("/api/security/**").permitAll()
                .pathMatchers(HttpMethod.GET,
                        "/api/productos/listar",
                        "/api/items/listar",
                        "/api/usuarios/listar",
                        "/api/items/listar/{id}/cantidad/{cantidad}",
                        "/api/productos/listar/{productoId}").permitAll()
                .pathMatchers(HttpMethod.GET, "/api/usuarios/listar/{usuarioId}", "/api/usuarios/listar/username/{username}").hasAnyRole("ADMIN", "USER")
                .pathMatchers("/api/productos/**", "/api/items/**", "/api/usuarios/**").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and().addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf().disable()
                .build();

    }

}
