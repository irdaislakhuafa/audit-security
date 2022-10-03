package com.audit.security.models.entity;

import javax.persistence.*;

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
    private String password;

    @ManyToOne
    private Role roles;
}
