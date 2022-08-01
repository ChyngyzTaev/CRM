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
    @Column(name = "monday", unique = true)
    String monday;

    @Column(name = "tuesday", unique = true)
    String tuesday;

    @Column(name = "wednesday", unique = true)
    String wednesday;

    @Column(name = "thursday", unique = true)
    String thursday;

    @Column(name = "friday", unique = true)
    String friday;

    @Column(name = "saturday", unique = true)
    String saturday;

    @Column(name = "sunday", unique = true)
    String sunday;
}
