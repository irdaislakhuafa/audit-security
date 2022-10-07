package com.audit.security.providers;

import com.audit.security.models.entity.User;

public interface JwtProvider {
    public String genTokenString(User claims);
}
