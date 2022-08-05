package com.example.CRM.service;

import com.example.CRM.entity.SubscriptionTypes;
import com.example.CRM.model.SubscriptionTypesModel;

public interface SubscriptionTypesService{
    SubscriptionTypesModel addSubscription(SubscriptionTypesModel subscriptionTypesModel);

    SubscriptionTypes setInActiveSubscriptionTypes(SubscriptionTypes subscriptionTypes, Long status);

    SubscriptionTypesModel getSubscriptionById(Long id);

    SubscriptionTypesModel updateSubscription(SubscriptionTypesModel subscriptionTypesModel);

    SubscriptionTypesModel deleteSubscriptionById(Long id);
}
