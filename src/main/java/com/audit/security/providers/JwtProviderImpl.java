package com.audit.security.providers;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.audit.security.models.entity.User;
import com.audit.security.models.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
                put("role", user.getRole());
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
}
