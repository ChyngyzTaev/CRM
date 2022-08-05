package com.example.CRM.entity;

import com.example.CRM.enums.WeekDayEnum;
import com.example.CRM.model.ChartModel;
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
    @JoinColumn(name = "schedule_id")
    ListExercises schedule;

    public ChartModel toModel() {
        return ChartModel.builder()
                .id(this.getId())
                .createDate(this.getCreateDate())
                .isActive(true)
                .updateDate(this.getUpdateDate())
                .build();
    }
}
