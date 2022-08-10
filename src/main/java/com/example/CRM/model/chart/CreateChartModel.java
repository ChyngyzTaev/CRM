package com.example.CRM.model.chart;

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
public class CreateChartModel {
    Long isActive;

    LocalDateTime createDate;

    LocalDateTime updateDate;

    WeekDayEnum weekDayEnum;
}
