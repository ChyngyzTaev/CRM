package com.example.CRM.model;

import com.example.CRM.enums.WeekDayEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChartModel extends BaseModel{
    WeekDayEnum weekDayEnum;

    ListExercisesModel scheduleModel;
}
