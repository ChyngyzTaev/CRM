package com.example.CRM.model;

import com.example.CRM.entity.SubscriptionTypes;
import com.example.CRM.entity.Users;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralRecordModel extends BaseModel{
    Users users;

    SubscriptionTypes types;
}