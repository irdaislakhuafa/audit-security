package com.audit.security.providers;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.audit.security.models.entity.User;

import io.jsonwebtoken.Claims;

public interface JwtProvider {
    public String genTokenString(User claims);

    public Claims getClaims(String tokenString);

    public boolean isExpired(String tokenString);

    public boolean isValid(String tokenString) throws UsernameNotFoundException;
}
