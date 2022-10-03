package com.audit.security.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.audit.security.models.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
