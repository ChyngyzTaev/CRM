package com.example.CRM.model.ListExercises;

import com.example.CRM.entity.ListExercises;
import com.example.CRM.enums.WeekDayEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateListExercisesModel {

    Long isActive;

    LocalDateTime createDate;

    LocalDateTime updateDate;

    WeekDayEnum weekDayEnum;

    String nameExercise;

    public ListExercises toListExercises(){
        return ListExercises.builder()
                .id(null)
                .isActive(isActive)
                .weekDayEnum(weekDayEnum)
                .nameExercise(nameExercise)
                .build();
    }
}
