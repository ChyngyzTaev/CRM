package com.example.CRM.entity;

import com.example.CRM.enums.WeekDayEnum;
import com.example.CRM.model.chart.ChartModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "chart")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chart extends BaseEntity {

    @Enumerated
    @Column(name = "weekday_enums")
    WeekDayEnum weekDayEnum;

    @OneToOne
    @JoinColumn(name = "list_exercises_id")
    ListExercises listExercises;

    public ChartModel toModel() {
        return ChartModel.builder()
                .id(this.getId())
                .weekDayEnum(weekDayEnum)
                .createDate(this.getCreateDate())
                .isActive(true)
                .updateDate(this.getUpdateDate())
                .build();
    }
}
