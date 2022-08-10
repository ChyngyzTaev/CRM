package com.example.CRM.model.subscriptionTypes;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionTypesModel{
    Long id;

    Long isActive;

    LocalDateTime createDate;

    LocalDateTime updateDate;

    Long numberOfMonth;

    Long numberOfWeek;

    Long numberOfDay;
}
