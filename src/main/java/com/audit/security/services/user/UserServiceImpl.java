package com.audit.security.services.user;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.audit.security.models.entity.Role;
import com.audit.security.models.entity.User;
import com.audit.security.models.repositories.RoleRepository;
import com.audit.security.models.repositories.UserRepository;
import com.audit.security.models.requests.UserRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
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
    public Optional<User> save(User entity) {
        return Optional.ofNullable(this.userRepository.save(entity));
    }

}
