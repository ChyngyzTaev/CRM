package com.example.CRM.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChartModel extends BaseModel{
    WeekdayModel weekdayModel;

    ScheduleModel scheduleModel;
}
