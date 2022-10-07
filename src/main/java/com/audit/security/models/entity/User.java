package com.audit.security.models.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYY hh:mm:ss")
    private LocalDateTime emailVerifiedAt;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Role roles;
}
