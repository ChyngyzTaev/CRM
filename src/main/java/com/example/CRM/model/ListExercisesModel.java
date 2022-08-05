package com.example.CRM.model;

import com.example.CRM.enums.WeekDayEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListExercisesModel extends BaseModel{
    WeekDayEnum weekDayEnum;

    String nameExercise;
}
