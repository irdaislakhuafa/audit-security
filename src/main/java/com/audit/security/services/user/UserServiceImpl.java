package com.audit.security.services.user;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.audit.security.models.entity.Role;
import com.audit.security.models.entity.User;
import com.audit.security.models.repositories.RoleRepository;
import com.audit.security.models.repositories.UserRepository;
import com.audit.security.models.requests.UserRequest;
import com.audit.security.utils.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User toEntity(UserRequest value) throws NoSuchElementException {
        var user = User.builder()
                .name(value.getName())
                .email(value.getEmail())
                .password(value.getPassword())
                .roles(this.roleRepository
                        .findByNameEqualsIgnoreCase(value.getRole())
                        .orElseGet(() -> {
                            var role = Role.builder()
                                    .name(value.getRole())
                                    .build();
                            role = this.roleRepository.save(role);
                            return role;
                        }))
                .build();
        return user;
    }

    @Override
    public ResponseEntity<ApiResponse<User>> save(User entity) {
        try {
            entity = this.userRepository.save(entity);
            return ApiResponse.success(entity);
        } catch (DataIntegrityViolationException e) {
            return ApiResponse.error("email with name \"" + entity.getEmail() + "\" already exists", entity);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage(), entity);
        }
    }

}
