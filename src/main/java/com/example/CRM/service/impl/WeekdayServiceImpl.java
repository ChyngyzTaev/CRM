package com.example.CRM.service.impl;

import com.example.CRM.entity.Weekday;
import com.example.CRM.model.WeekdayModel;
import com.example.CRM.repository.WeekdayRepository;
import com.example.CRM.service.WeekdayService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WeekdayServiceImpl implements WeekdayService {
    @Autowired
    private WeekdayRepository repository;


    @Override
    public Weekday save(Weekday weekday) {
        return null;
    }

    @Override
    public Weekday getById(Long id) {
        return null;
    }

    @Override
    public List<Weekday> getAll() {
        return null;
    }

    @Override
    public List<WeekdayModel> getAllWeekday() {
        return null;
    }
}
