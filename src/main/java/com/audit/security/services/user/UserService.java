package com.audit.security.services.user;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;

import com.audit.security.models.entity.User;
import com.audit.security.models.requests.UserLoginRequest;
import com.audit.security.models.requests.UserRequest;
import com.audit.security.services.BasicService;
import com.audit.security.utils.ApiResponse;

public interface UserService extends BasicService<User> {
	User toEntity(UserRequest value) throws NoSuchElementException;

	ResponseEntity<ApiResponse<Object>> login(UserLoginRequest request);

}
