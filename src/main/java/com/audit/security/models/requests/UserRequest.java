package com.audit.security.models.requests;

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserRequest {
    @NotBlank(message = "cannot be blank")
    @NotEmpty(message = "cannot be empty")
    private String name;

    @Email(message = "email not valid")
    @NotBlank(message = "cannot be blank")
    @NotEmpty(message = "cannot be empty")
    private String email;

    @NotBlank(message = "cannot be blank")
    @NotEmpty(message = "cannot be empty")
    private String password;

    @NotBlank(message = "cannot be blank")
    @NotEmpty(message = "cannot be empty")
    private String role;
}
