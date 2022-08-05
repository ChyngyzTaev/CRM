package com.example.CRM.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralRecordModel extends BaseModel{
    UserModel usersModel;

    SubscriptionTypesModel subscriptionTypesModel;
}