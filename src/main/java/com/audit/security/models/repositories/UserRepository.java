package com.audit.security.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.audit.security.models.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
