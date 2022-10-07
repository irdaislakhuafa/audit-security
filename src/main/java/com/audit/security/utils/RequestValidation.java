package com.audit.security.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.Errors;

public class RequestValidation {
    public static Map<?, ?> getErrors(Errors errors) {
        final var messages = new HashMap<>();
        for (var error : errors.getFieldErrors()) {
            messages.put(error.getField(), error.getDefaultMessage());
        }
        return messages;
    }

}
