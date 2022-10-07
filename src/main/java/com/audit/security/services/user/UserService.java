package com.audit.security.services.user;

import com.audit.security.models.entity.User;
import com.audit.security.models.requests.UserRequest;
import com.audit.security.services.BasicService;
import java.util.NoSuchElementException;

public interface UserService extends BasicService<User> {
	User toEntity(UserRequest value) throws NoSuchElementException;
	
}
