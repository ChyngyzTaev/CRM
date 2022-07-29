package com.example.CRM.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subscription_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionTypes extends BaseEntity {
    @Column(name = "number_of_month")
    Long numberOfMonth;

    @Column(name = "number_of_week")
    Long numberOfWeek;

    @Column(name = "number_of_day")
    Long numberOfDay;
}
