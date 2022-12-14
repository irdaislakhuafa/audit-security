package com.audit.security.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ApiResponse<T> implements Serializable {
    private Boolean status;
    private Object message;
    private T data;

    public static <A> ResponseEntity<ApiResponse<A>> error(Map<?, ?> value, A type) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.<A>builder()
                        .status(false)
                        .data(null)
                        .message(value)
                        .build());
    }

    public static <A> ResponseEntity<ApiResponse<A>> error(String error, A type) {
        return error(new HashMap<>() {
            {
                put("error", error);
            }
        }, type);
    }

    public static <A> ResponseEntity<ApiResponse<A>> success(A value) {
        return ResponseEntity.ok()
                .body(ApiResponse.<A>builder()
                        .status(true)
                        .data(value)
                        .message("success")
                        .build());
    }
}
