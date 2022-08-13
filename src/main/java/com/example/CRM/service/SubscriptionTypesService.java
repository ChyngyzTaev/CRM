package com.example.CRM.service;

import com.example.CRM.entity.SubscriptionTypes;
import com.example.CRM.model.subscriptionTypes.CreateSubscriptionTypesModel;
import com.example.CRM.model.subscriptionTypes.SubscriptionTypesModel;
import com.example.CRM.model.subscriptionTypes.UpdateSubscriptionTypesModel;

public interface SubscriptionTypesService{
    SubscriptionTypesModel addSubscription(CreateSubscriptionTypesModel subscriptionTypesModel);

    SubscriptionTypes setInActiveSubscriptionTypes(SubscriptionTypes subscriptionTypes, Long status);

    SubscriptionTypesModel getSubscriptionById(Long id);

    boolean updateSubscription(UpdateSubscriptionTypesModel subscriptionTypesModel);

    SubscriptionTypesModel deleteSubscriptionById(Long id);
}
