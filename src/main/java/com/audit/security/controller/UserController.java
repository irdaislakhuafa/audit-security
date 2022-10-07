package com.audit.security.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.audit.security.models.entity.User;
import com.audit.security.models.requests.UserRequest;
import com.audit.security.services.user.UserServiceImpl;
import com.audit.security.utils.ApiResponse;
import com.audit.security.utils.RequestValidation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = { "/users" })
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping
    public ResponseEntity<ApiResponse<User>> create(
            @RequestBody(required = true) @Valid UserRequest request,
            Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.error(RequestValidation.getErrors(errors), new User());
        }
        var user = userServiceImpl.toEntity(request);
        return userServiceImpl.save(user);
    }
}
