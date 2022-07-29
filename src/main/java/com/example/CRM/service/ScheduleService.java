package com.example.CRM.service;

import com.example.CRM.entity.Schedule;
import com.example.CRM.model.ScheduleModel;

import java.util.List;

public interface ScheduleService extends BaseService<Schedule> {
    ScheduleModel addNewSchedule(ScheduleModel scheduleModel);

    ScheduleModel getScheduleById(Long id);

    List<ScheduleModel> getAllSchedule();

    void deleteSchedule();
}
