package com.example.CRM.service;

import com.example.CRM.entity.Weekday;
import com.example.CRM.model.WeekdayModel;

import java.util.List;

public interface WeekdayService extends BaseService<Weekday> {
    WeekdayModel addWeekday(WeekdayModel weekdayModel);

    List<WeekdayModel> getAllWeekday();
}
