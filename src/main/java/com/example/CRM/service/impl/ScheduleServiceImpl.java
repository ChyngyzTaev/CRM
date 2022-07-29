package com.example.CRM.service.impl;

import com.example.CRM.entity.Schedule;
import com.example.CRM.model.ScheduleModel;
import com.example.CRM.repository.ScheduleRepository;
import com.example.CRM.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository repository;

    @Override
    public Schedule save(Schedule schedule) {
        return null;
    }

    @Override
    public Schedule getById(Long id) {
        return null;
    }

    @Override
    public List<Schedule> getAll() {
        return null;
    }

    @Override
    public ScheduleModel addNewSchedule(ScheduleModel scheduleModel) {
        return null;
    }

    @Override
    public ScheduleModel getScheduleById(Long id) {
        return null;
    }

    @Override
    public List<ScheduleModel> getAllSchedule() {
        return null;
    }

    @Override
    public void deleteSchedule() {

    }
}
