package com.example.CRM.service.impl;

import com.example.CRM.entity.SubscriptionTypes;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.subscriptionTypes.CreateSubscriptionTypesModel;
import com.example.CRM.model.subscriptionTypes.SubscriptionTypesModel;
import com.example.CRM.model.subscriptionTypes.UpdateSubscriptionTypesModel;
import com.example.CRM.repository.SubscriptionTypesRepository;
import com.example.CRM.service.SubscriptionTypesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
public class SubscriptionTypesServiceImpl implements SubscriptionTypesService {
    @Autowired
    private SubscriptionTypesRepository subscriptionTypesRepository;


    @Override
    public SubscriptionTypesModel addSubscription(CreateSubscriptionTypesModel subscriptionTypesModel) {
        SubscriptionTypes subscriptionTypes = subscriptionTypesModel.toSubscriptionTypes();
        subscriptionTypesRepository.save(subscriptionTypes);
        return subscriptionTypes.toModel();
    }

    @Override
    public SubscriptionTypes setInActiveSubscriptionTypes(SubscriptionTypes subscriptionTypes, Long status) {
        subscriptionTypes.setIsActive(subscriptionTypes.getIsActive());
        return subscriptionTypesRepository.save(subscriptionTypes);
    }

    @Override
    public SubscriptionTypesModel getSubscriptionById(Long id) {
        SubscriptionTypes subscriptionTypes = subscriptionTypesRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Абонемент связанный с идентификатором " + id + " не найден"));
        return subscriptionTypes.toModel();
    }

    @Override
    public boolean updateSubscription(UpdateSubscriptionTypesModel subscriptionTypesModel) {
        if (subscriptionTypesModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (subscriptionTypesModel.getId() == null) {
            throw new InvalidParameterException("Id абонемента  не может иметь пустое значени");
        }
        SubscriptionTypes subscriptionTypes = subscriptionTypesRepository.getById(subscriptionTypesModel.getId());
        if (subscriptionTypes == null) {
            throw new UserNotFoundException
                    ("Абонимент по id не нанйдено " + subscriptionTypesModel.getId());
        }
        subscriptionTypes.setNumberOfMonth(subscriptionTypesModel.getNumberOfMonth());
        subscriptionTypes.setNumberOfWeek(subscriptionTypesModel.getNumberOfWeek());
        subscriptionTypes.setNumberOfDay(subscriptionTypesModel.getNumberOfDay());
        subscriptionTypes.setCreateDate(subscriptionTypesModel.getCreateDate());
        subscriptionTypes = subscriptionTypesRepository.save(subscriptionTypes);
        return subscriptionTypes.getId() != null;
    }

    @Override
    public SubscriptionTypesModel deleteSubscriptionById(Long id) {
        SubscriptionTypes subscriptionTypes = getById(id);
        SubscriptionTypes deleteSubscription = setInActiveSubscriptionTypes(subscriptionTypes, -1L);
        return deleteSubscription.toModel();
    }

    public SubscriptionTypes getById(Long id) {
        return subscriptionTypesRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException
                                ("Абонемент связанный с идентификатором " + id + " не найдено"));
    }
}
