package com.example.CRM.entity;

import com.example.CRM.enums.WeekDayEnum;
import com.example.CRM.model.chart.ChartModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chart")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chart extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "weekday_enums", nullable = false)
    WeekDayEnum weekDayEnum;

    @OneToMany
    @JoinColumn(name = "list_exercises_id")
    List<ListExercises> listExercises;

    public ChartModel toModel() {
        return ChartModel.builder()
                .id(this.getId())
                .weekDayEnum(weekDayEnum)
                .build();
    }
}
