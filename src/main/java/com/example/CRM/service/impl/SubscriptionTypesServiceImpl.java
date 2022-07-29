package com.example.CRM.service.impl;

import com.example.CRM.entity.SubscriptionTypes;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.SubscriptionTypesModel;
import com.example.CRM.repository.SubscriptionTypesRepository;
import com.example.CRM.service.SubscriptionTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionTypesServiceImpl implements SubscriptionTypesService {
    @Autowired
    private SubscriptionTypesRepository repository;


    @Override
    public SubscriptionTypes save(SubscriptionTypes subscriptionTypes) {
        return null;
    }

    @Override
    public SubscriptionTypes getById(Long id) {
        return null;
    }

    @Override
    public List<SubscriptionTypes> getAll() {
        return null;
    }

    @Override
    public SubscriptionTypesModel addSubscription(SubscriptionTypesModel subscriptionTypesModel) {
        return null;
    }

    @Override
    public SubscriptionTypes getSubscriptionById(Long id) {
        return null;
    }

    @Override
    public SubscriptionTypesModel updateSubscription(SubscriptionTypesModel subscriptionTypesModel) {
        return null;
    }

    @Override
    public void deleteSubscriptionById(Long id) {

    }
}
