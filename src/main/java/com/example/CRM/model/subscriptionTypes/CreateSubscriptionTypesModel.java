package com.example.CRM.model.subscriptionTypes;

import com.example.CRM.entity.SubscriptionTypes;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSubscriptionTypesModel {

    Long isActive;

    LocalDateTime createDate;

    LocalDateTime updateDate;

    Long numberOfMonth;

    Long numberOfWeek;

    Long numberOfDay;


    public SubscriptionTypes toSubscriptionTypes(){
        return SubscriptionTypes.builder()
                .id(null)
                .numberOfMonth(numberOfMonth)
                .numberOfWeek(numberOfWeek)
                .numberOfDay(numberOfDay)
                .build();
    }
}
