package com.example.CRM.service.impl;

import com.example.CRM.entity.SubscriptionTypes;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.SubscriptionTypesModel;
import com.example.CRM.repository.SubscriptionTypesRepository;
import com.example.CRM.service.SubscriptionTypesService;
import org.springframework.beans.factory.annotation.Autowired;

public class SubscriptionTypesServiceImpl implements SubscriptionTypesService {
    @Autowired
    private SubscriptionTypesRepository repository;
    @Override
    public SubscriptionTypesModel addSubscription(SubscriptionTypesModel subscriptionTypesModel) {
        SubscriptionTypes subscriptionTypes = new SubscriptionTypes();
        subscriptionTypes.setName(subscriptionTypesModel.getName());
        subscriptionTypes.setCountMonth(subscriptionTypesModel.getCountMonth());
        repository.save(subscriptionTypes);
        return subscriptionTypesModel;
    }

    @Override
    public SubscriptionTypes getSubscriptionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Абонемент связонный с id " + id + "не найден"));
    }

    @Override
    public void deleteSubscriptionById(Long id) {
        repository.deleteById(id);
    }
}
