package com.audit.security.providers;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.audit.security.models.entity.User;
import com.audit.security.models.repositories.UserRepository;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtProviderImpl implements JwtProvider {
    private final UserRepository userRepository;
    private final long milisecond = 1000L;
    private final long seconds = (milisecond * 60);

    @Value(value = "${jwt.expiration.in.minute}")
    private int jwtExpInMinute;

    @Value(value = "${jwt.secret.key}")
    private String secretKey;

    @Override
    public String genTokenString(User user) {
        final var claims = new HashMap<String, Object>() {
            {
                put("userId", user.getId());
                put("role", user.getRole().getName());
            }
        };

        final var createdAt = new Date(System.currentTimeMillis());
        final var expiredAt = new Date(createdAt.getTime() + (seconds * jwtExpInMinute));

        final var token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdAt)
                .setExpiration(expiredAt)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return token;
    }

    @Override
    public Claims getClaims(String tokenString) {
        final var claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(tokenString)
                .getBody();
        return claims;
    }

    @Override
    public boolean isExpired(String tokenString) {
        final var claims = this.getClaims(tokenString);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    @Override
    public boolean isValid(String tokenString) throws UsernameNotFoundException {
        final var claims = this.getClaims(tokenString);
        final var user = this.userRepository
                .findById(claims.get("userId", Integer.class))
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return (this.isExpired(tokenString) || user.getRole().getName().equalsIgnoreCase(claims.get("role", String.class)));
    }
}
