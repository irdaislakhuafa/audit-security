package com.audit.security.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class DefaultExceptionHandling {
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<Object> accessDenied(AccessDeniedException e) {
        return new ResponseEntity<Object>(
                ApiResponse.builder()
                        .status(false)
                        .message("access denied, user not allowed to access this endpoint")
                        .data(null)
                        .build(),
                HttpStatus.FORBIDDEN);
    }
}
