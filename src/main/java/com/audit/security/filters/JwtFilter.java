package com.audit.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.audit.security.models.repositories.UserRepository;
import com.audit.security.providers.JwtProviderImpl;

import lombok.RequiredArgsConstructor;

@Order(value = 1)
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	private final JwtProviderImpl jwtProvider;
	private final UserRepository userRepository;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {

		var authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.startsWith("Bearer ")) {
			authorization = authorization.substring("Bearer ".length());
			if (this.jwtProvider.isValid(authorization)
					&& SecurityContextHolder.getContext().getAuthentication() == null) {
				final var user = this.userRepository.findById(
						this.jwtProvider.getClaims(authorization).get("userId", Integer.class))
						.orElseThrow(() -> new UsernameNotFoundException("user not found"));
				final var auth = new UsernamePasswordAuthenticationToken(
						user.getEmail(),
						user.getPassword(),
						user.getAuthorities()) {
					{
						setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					}
				};

				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		filterChain.doFilter(request, response);
	}

}
