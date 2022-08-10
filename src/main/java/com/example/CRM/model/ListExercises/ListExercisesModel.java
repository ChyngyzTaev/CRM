package com.example.CRM.model.ListExercises;

import com.example.CRM.enums.WeekDayEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListExercisesModel {
    Long id;

    Long isActive;

    LocalDateTime createDate;

    LocalDateTime updateDate;

    WeekDayEnum weekDayEnum;

    String nameExercise;
}
