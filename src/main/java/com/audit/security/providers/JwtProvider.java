package com.audit.security.providers;

import com.audit.security.models.entity.User;

import io.jsonwebtoken.Claims;

public interface JwtProvider {
    public String genTokenString(User claims);

    public Claims getClaims(String tokenString);
}
