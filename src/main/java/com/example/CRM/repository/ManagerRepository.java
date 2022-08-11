package com.example.CRM.repository;

import com.example.CRM.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<User, Long> {
    Optional<User> findManagerByUsername(String username);

    Optional<User> findManagerByEmail(String email);

    Optional<User> deleteByUsername(String username);

}
