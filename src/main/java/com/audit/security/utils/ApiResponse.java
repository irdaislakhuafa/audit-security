package com.audit.security.utils;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ApiResponse<T> {
    private Boolean status;
    private Object message;
    private T data;

    public static <A> ResponseEntity<ApiResponse<A>> error(A value) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.<A>builder()
                        .status(false)
                        .data(null)
                        .message(value)
                        .build());
    }
}
