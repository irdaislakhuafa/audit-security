package com.audit.security.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.audit.security.models.requests.UserRequest;
import com.audit.security.services.user.UserServiceImpl;
import com.audit.security.utils.ApiResponse;
import com.audit.security.utils.RequestValidation;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = { "/users" })
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody(required = true) @Valid UserRequest request, Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.error(RequestValidation.getErrors(errors));
        }
        try {

            var user = userServiceImpl.toEntity(request);
            var savedUser = userServiceImpl.save(user);
            if (savedUser.isEmpty()) {
                return ApiResponse.error("unknown error");
            }
            return ApiResponse.success(savedUser.get());
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

}
