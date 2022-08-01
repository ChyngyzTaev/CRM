package com.example.CRM.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "chart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chart extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "weekday_id", unique = true, nullable = false)
    Weekday weekday;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    Schedule schedule;
}
