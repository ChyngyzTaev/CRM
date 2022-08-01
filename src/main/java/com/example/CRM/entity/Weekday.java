package com.example.CRM.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "weekday")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Weekday extends BaseEntity{
    @Column(name = "monday", unique = true, nullable = false)
    String monday;

    @Column(name = "tuesday", unique = true, nullable = false)
    String tuesday;

    @Column(name = "wednesday", unique = true, nullable = false)
    String wednesday;

    @Column(name = "thursday", unique = true, nullable = false)
    String thursday;

    @Column(name = "friday", unique = true, nullable = false)
    String friday;

    @Column(name = "saturday", unique = true, nullable = false)
    String saturday;

    @Column(name = "sunday", unique = true, nullable = false)
    String sunday;
}
