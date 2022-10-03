package com.audit.security.models.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BasicEntity {
    private String name;
    private String email;
    private LocalDateTime emailVerifiedAt;
    private String password;

    @ManyToOne
    private Role roles;
}
