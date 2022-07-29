package com.example.CRM.repository;

import com.example.CRM.entity.Weekday;
import com.example.CRM.model.WeekdayModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekdayRepository extends JpaRepository<Weekday, Long> {
    WeekdayModel getDayByName(WeekdayModel weekdayModel);
}
