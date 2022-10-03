package com.audit.security.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.audit.security.models.requests.UserRequest;
import com.audit.security.utils.ApiResponse;
import com.audit.security.utils.RequestValidation;

@RestController
@RequestMapping(value = { "/users" })
public class UserController {
    @PostMapping
    public ResponseEntity<?> create(@RequestBody(required = true) @Valid UserRequest request, Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.error(RequestValidation.getErrors(errors));
        }
        return ResponseEntity.ok(request);
    }

}
