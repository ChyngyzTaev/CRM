package com.example.CRM.repository;

import com.example.CRM.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<User, Long> {
    Optional<User> findTrainerByUsername(String username);

    Optional<User> findTrainerByEmail(String email);

    Optional<User> deleteByUsername(String username);
}
