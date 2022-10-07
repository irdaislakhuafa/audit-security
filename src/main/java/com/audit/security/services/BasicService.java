package com.audit.security.services;

import com.audit.security.models.entity.User;
import com.audit.security.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface BasicService<ENTITY> {
	ResponseEntity<ApiResponse<User>> save(ENTITY entity);
}
