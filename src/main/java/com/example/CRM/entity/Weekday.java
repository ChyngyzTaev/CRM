package com.example.CRM.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String monday;

    String tuesday;

    String wednesday;

    String thursday;

    String friday;

    String Saturday;

    String sunday;
}
