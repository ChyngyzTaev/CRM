package com.example.CRM.repository;

import com.example.CRM.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneralRecordRepository extends JpaRepository<GeneralRecord, Long> {
    Optional<Chart> findChartById(Long id);

    Optional<ListExercises> findListExercisesById(Long id);

    Optional<UserRole> findRoleById(Long id);

    Optional<SubscriptionTypes> findSubscriptionTypesById(Long id);

    Optional<User> findUserById(Long id);
}
