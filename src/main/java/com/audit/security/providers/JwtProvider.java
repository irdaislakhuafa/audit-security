package com.audit.security.providers;

import com.audit.security.models.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface JwtProvider {
	String genTokenString(User claims);
	
	Claims getClaims(String tokenString);
	
	boolean isExpired(String tokenString);
	
	boolean isValid(String tokenString) throws UsernameNotFoundException;
}
