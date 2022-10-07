package com.audit.security.services;

import org.springframework.http.ResponseEntity;

import com.audit.security.models.entity.User;
import com.audit.security.utils.ApiResponse;

public interface BasicService<ENTITY> {
    public ResponseEntity<ApiResponse<User>> save(ENTITY entity);
}
