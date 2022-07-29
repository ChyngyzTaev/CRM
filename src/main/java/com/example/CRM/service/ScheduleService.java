package com.example.CRM.service;

import com.example.CRM.entity.Schedule;
import com.example.CRM.entity.SubscriptionTypes;
import com.example.CRM.model.ScheduleModel;

import java.util.List;

public interface ScheduleService extends BaseService<Schedule> {
    ScheduleModel addNewSchedule(ScheduleModel scheduleModel);

    Schedule setInActiveUser(Schedule schedule, Long status);

    ScheduleModel getScheduleById(Long id);

    List<ScheduleModel> getAllSchedule();

    ScheduleModel updateSchedule(ScheduleModel scheduleModel);

    ScheduleModel deleteScheduleById(Long id);
}
