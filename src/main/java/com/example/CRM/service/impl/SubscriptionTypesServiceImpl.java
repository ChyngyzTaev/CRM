package com.example.CRM.service.impl;

import com.example.CRM.convert.BaseConvert;
import com.example.CRM.entity.SubscriptionTypes;
import com.example.CRM.entity.UsersInformation;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.SubscriptionTypesModel;
import com.example.CRM.repository.SubscriptionTypesRepository;
import com.example.CRM.service.SubscriptionTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class SubscriptionTypesServiceImpl implements SubscriptionTypesService {
    @Autowired
    private SubscriptionTypesRepository repository;

    @Autowired
    private BaseConvert<SubscriptionTypesModel, SubscriptionTypes> convert;


    @Override
    public SubscriptionTypesModel addSubscription(SubscriptionTypesModel subscriptionTypesModel) {
        SubscriptionTypes subscriptionTypes = new SubscriptionTypes();
        subscriptionTypes.setId(subscriptionTypesModel.getId());
        subscriptionTypes.setNumberOfMonth(subscriptionTypesModel.getNumberOfMonth());
        subscriptionTypes.setNumberOfWeek(subscriptionTypesModel.getNumberOfWeek());
        subscriptionTypes.setNumberOfDay(subscriptionTypesModel.getNumberOfDay());
        subscriptionTypes.setCreateDate(subscriptionTypesModel.getCreateDate());
        repository.save(subscriptionTypes);
        return subscriptionTypesModel;
    }

    @Override
    public SubscriptionTypes setInActiveUser(SubscriptionTypes subscriptionTypes, Long status) {
        subscriptionTypes.setActive(true);
        return repository.save(subscriptionTypes);
    }

    @Override
    public SubscriptionTypesModel getSubscriptionById(Long id) {
        return convert.convertFromEntity(getById(id));
    }

    @Override
    public SubscriptionTypesModel updateSubscription(SubscriptionTypesModel subscriptionTypesModel) {
        if (subscriptionTypesModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (subscriptionTypesModel.getId() == null) {
            throw new InvalidParameterException("Id информации не может быть пустым");
        }

        SubscriptionTypes subscriptionTypes = repository.getById(subscriptionTypesModel.getId());
        if (subscriptionTypes == null) {
            throw new UserNotFoundException
                    ("Абонимент по id не нанйдено " + subscriptionTypesModel.getId());
        }

        subscriptionTypes.setNumberOfMonth(subscriptionTypesModel.getNumberOfMonth());
        subscriptionTypes.setNumberOfWeek(subscriptionTypesModel.getNumberOfWeek());
        subscriptionTypes.setNumberOfDay(subscriptionTypesModel.getNumberOfDay());

        subscriptionTypes = repository.save(subscriptionTypes);

        return subscriptionTypesModel;
    }

    @Override
    public SubscriptionTypesModel deleteSubscriptionById(Long id) {
        SubscriptionTypes subscriptionTypes = getById(id);
        SubscriptionTypes deleteSubscription = setInActiveUser(subscriptionTypes, -1L);
        return convert.convertFromEntity(deleteSubscription);
    }

    @Override
    public SubscriptionTypes save(SubscriptionTypes subscriptionTypes) {
        return repository.save(subscriptionTypes);
    }

    @Override
    public SubscriptionTypes getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException("id связанный с идентификатором " + id + " не найдено"));
    }

    @Override
    public List<SubscriptionTypes> getAll() {
        return repository.findAll();
    }
}
