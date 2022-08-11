package com.example.CRM.service;

import com.example.CRM.entity.*;

public interface GeneralRecordService {
    Chart getChartById(Long id);


    ListExercises getListExercisesById(Long id);


    UserRole getRolById(Long id);


    SubscriptionTypes getSubscriptionTypesById(Long id);


    User getUserById(Long id);
}
