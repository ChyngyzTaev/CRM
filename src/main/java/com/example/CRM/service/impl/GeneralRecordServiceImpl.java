package com.example.CRM.service.impl;

import com.example.CRM.entity.*;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.repository.GeneralRecordRepository;
import com.example.CRM.service.GeneralRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralRecordServiceImpl implements GeneralRecordService {
    @Autowired
    private GeneralRecordRepository generalRecordRepository;


    @Override
    public Chart getChartById(Long id) {
        return generalRecordRepository
                .findChartById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Информация о Графике связанная с идентификатором " + id + " не найден"));
    }

    @Override
    public ListExercises getListExercisesById(Long id) {
        return generalRecordRepository
                .findListExercisesById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Информация о Списке упражнений связанная с идентификатором " + id + " не найден"));
    }

    @Override
    public UserRole getRolById(Long id) {
        return generalRecordRepository
                .findRoleById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Информация о Роли связанная с идентификатором " + id + " не найден"));
    }

    @Override
    public SubscriptionTypes getSubscriptionTypesById(Long id) {
        return generalRecordRepository
                .findSubscriptionTypesById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Информация о Абонименте связанная с идентификатором " + id + " не найден"));
    }

    @Override
    public User getUserById(Long id) {
        return generalRecordRepository
                .findUserById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Информация о Пользователе связанная с идентификатором " + id + " не найден"));
    }
}
