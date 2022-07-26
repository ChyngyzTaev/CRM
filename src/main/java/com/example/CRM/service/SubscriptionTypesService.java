package com.example.CRM.service;

import com.example.CRM.entity.SubscriptionTypes;
import com.example.CRM.model.SubscriptionTypesModel;

public interface SubscriptionTypesService {
    SubscriptionTypesModel addSubscription(SubscriptionTypesModel subscriptionTypesModel);

    SubscriptionTypes getSubscriptionById(Long id);

    SubscriptionTypesModel updateSubscription(SubscriptionTypesModel subscriptionTypesModel);

    void deleteSubscriptionById(Long id);
}
