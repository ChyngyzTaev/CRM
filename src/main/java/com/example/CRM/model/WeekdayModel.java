package com.example.CRM.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeekdayModel extends BaseModel{
    String monday;

    String tuesday;

    String wednesday;

    String thursday;

    String friday;

    String Saturday;

    String sunday;
}
