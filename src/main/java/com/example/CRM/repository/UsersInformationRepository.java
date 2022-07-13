package com.example.CRM.repository;

import com.example.CRM.entity.UsersInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersInformationRepository extends JpaRepository<UsersInformation, Long> {
}
