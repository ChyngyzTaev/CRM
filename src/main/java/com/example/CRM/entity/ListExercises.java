package com.example.CRM.entity;

import com.example.CRM.enums.WeekDayEnum;
import com.example.CRM.model.ListExercises.ListExercisesModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "list_exercises")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListExercises extends BaseEntity{
    @Column(name = "name_exercise", nullable = false)
    String nameExercise;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekday_enums", nullable = false)
    WeekDayEnum weekDayEnum;

    @ManyToOne
    @JoinColumn(name = "chart_id")
    Chart chart;

    public ListExercisesModel toModel(){
        return ListExercisesModel.builder()
                .id(this.getId())
                .weekDayEnum(weekDayEnum)
                .nameExercise(nameExercise)
                .build();
    }
}
