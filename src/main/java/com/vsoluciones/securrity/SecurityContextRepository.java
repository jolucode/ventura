package com.vsoluciones.securrity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//Clase S6
@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {

    Logger logger = LoggerFactory.getLogger(SecurityContextRepository.class);


    private final AuthenticationManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return null;
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        try {

            if (authHeader != null) {
                if (authHeader.startsWith("Bearer ") || authHeader.startsWith("bearer ")) {
                    final int TOKEN_POSITION = 1;
                    String token = authHeader.split(" ")[TOKEN_POSITION];
                    Authentication auth = new UsernamePasswordAuthenticationToken(token, token);

                    return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
                } else {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorized");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorized");
            }

        } catch (ResponseStatusException e) {
            // Este catch captura los errores lanzados manualmente
            logger.error("SecurityContext loading error: " + e.getMessage(), e);
            return Mono.error(e);
        } catch (Exception e) {
            // Este catch captura cualquier otro error inesperado
            logger.error("Unexpected error occurred while loading SecurityContext", e);
            return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unexpected error", e));
        }
    }
}
