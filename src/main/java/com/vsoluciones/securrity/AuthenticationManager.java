package com.vsoluciones.securrity;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

//Clase S5
@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
  Logger logger = LoggerFactory.getLogger(AuthenticationManager.class);
  private final JwtUtil jwtUtil;

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    try {
      String token = authentication.getCredentials().toString();
      String user = jwtUtil.getUsernameFromToken(token);

      if (user != null && jwtUtil.validateToken(token)) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        List<String> roles = claims.get("roles", List.class);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, roles.stream().map(SimpleGrantedAuthority::new).toList());

        return Mono.just(auth);
      } else {
        return Mono.error(new CustomJwtException("Token not valid or expired"));
      }
    } catch (CustomJwtException e) {
      // Aquí puedes manejar la excepción personalizada o dejarla propagarse
      Logger logger = LoggerFactory.getLogger(this.getClass());
      logger.error(e.getMessage());
      return Mono.error(e);
    }
  }
}
