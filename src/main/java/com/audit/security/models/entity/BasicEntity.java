package com.audit.security.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BasicEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYY hh:mm:ss")
    private LocalDateTime createdAt;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYY hh:mm:ss")
    private LocalDateTime updatedAt;

    private String updatedBy;

    @PrePersist
    public void onInsert() {
        this.createdAt = LocalDateTime.now();
        this.createdBy = this.getCurrentUser();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.createdBy = this.getCurrentUser();
    }

    private String getCurrentUser() {
        String name;
        try {
            name = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e) {
            name = "system";
        }
        return name;
    }
}
