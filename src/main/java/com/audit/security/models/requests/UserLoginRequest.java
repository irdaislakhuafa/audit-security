package com.audit.security.models.requests;

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserLoginRequest {
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    private String username;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    private String password;
}
