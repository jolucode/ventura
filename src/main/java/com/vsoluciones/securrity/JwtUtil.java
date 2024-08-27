package com.vsoluciones.securrity;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//Clase S4
@Component
public class JwtUtil implements Serializable {

  Logger logger = LoggerFactory.getLogger(JwtUtil.class);

  @Value("${jjwt.secret}") //EL Expression Language
  private String secret;

  @Value("${jjwt.expiration}")
  private String expirationTime;

  public String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>(); //payload
    claims.put("roles", user.getRoles());
    claims.put("username", user.getUsername());
    claims.put("password-demo", "sample");

    return doGenerateToken(claims, user.getUsername());
  }

  private String doGenerateToken(Map<String, Object> claims, String username) {
    Long expirationTimeLong = Long.parseLong(expirationTime);

    final Date createdDate = new Date();
    final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000); //45/46 minutos

    SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(username)
        .setIssuedAt(createdDate)
        .setExpiration(expirationDate)
        .signWith(key)
        .compact();
  }


  ////////////////////////////////////////////////////////
  public Claims getAllClaimsFromToken(String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());
      return Jwts.parser()
              .setSigningKey(key)
              .build()
              .parseClaimsJws(token)
              .getBody();
    } catch (ExpiredJwtException e) {
      // Registra el error específico de token expirado
      logger.error("Token expired: " + e.getMessage());
      throw new CustomJwtException("Token has expired", e);
    } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
      // Registra otros errores relacionados con JWT
      logger.error("JWT Error: " + e.getMessage());
      throw new CustomJwtException("Genera token");
    }
  }

  public String getUsernameFromToken(String token) {
    return getAllClaimsFromToken(token).getSubject();
  }

  public Date getExpirationDateFromToken(String token) {
    return getAllClaimsFromToken(token).getExpiration();
  }

  private boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public boolean validateToken(String token) {
    return !isTokenExpired(token);
  }
}
