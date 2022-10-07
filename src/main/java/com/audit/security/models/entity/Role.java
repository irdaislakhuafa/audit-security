package com.audit.security.models.entity;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Role extends BasicEntity {

    @Column(unique = true, nullable = false)
    private String name;
}
