package com.audit.security.services.user;

import java.util.NoSuchElementException;

import com.audit.security.models.entity.User;
import com.audit.security.models.requests.UserRequest;
import com.audit.security.services.BasicService;

public interface UserService extends BasicService<User> {
    public User toEntity(UserRequest value) throws NoSuchElementException;

}
