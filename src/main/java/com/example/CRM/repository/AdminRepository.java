package com.example.CRM.repository;

import com.example.CRM.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<User ,Long> {
    Optional<User> findAdminByUsername(String username);

    Optional<User> findAdminByEmail(String email);

    Optional<User> deleteByUsername(String username);
}
