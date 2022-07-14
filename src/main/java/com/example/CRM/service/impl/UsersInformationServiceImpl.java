package com.example.CRM.service.impl;

import com.example.CRM.entity.UsersInformation;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.NullException;
import com.example.CRM.model.UsersInformationModel;
import com.example.CRM.repository.UsersInformationRepository;
import com.example.CRM.service.UsersInformationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.InvalidParameterException;
import java.util.List;

public class UsersInformationServiceImpl implements UsersInformationService {

    @Autowired
    private UsersInformationRepository informationRepository;

    @Override
    public UsersInformationModel addUserInfo(UsersInformationModel informationModel) {
        UsersInformation usersInformation = new UsersInformation();
        informationModel.setFullName(usersInformation.getFullName());
        informationModel.setBirthday(usersInformation.getBirthDay());
        informationModel.setPhoneNumber(usersInformation.getPhoneNumber());
        informationModel.setUsers(usersInformation.getUsers());
        informationRepository.save(usersInformation);
        return informationModel;
    }

    @Override
    public UsersInformation getUserInfoById(Long id) {
        return informationRepository.findById(id).
                orElseThrow(()->
                        new NotFoundException("Информация о пользователе связанный с Id" + id + "не найдено"));
    }

    @Override
    public List<UsersInformation> getAllUserInfo(UsersInformationModel informationModel) {
        return informationRepository.findAll();
    }

    @Override
    public boolean updateUserInfo(UsersInformationModel informationModel) {
        if (informationModel == null) {
                throw new NullException("Созданная информация о пользователе имеет " + "пустое" + "значение");
            } else if (informationModel.getFullName() == null || informationModel.getFullName().equals("")) {
                throw new InvalidParameterException("Информация не может быть пустой");
            } else if (informationModel.getId() == null) {
                throw new InvalidParameterException("Id информации не может быть пустым");
            }

        UsersInformation usersInformation = informationRepository.getById(informationModel.getId());
        if (usersInformation == null) {
            throw new NullException("Инфорация о пользоваетеле по" + "Id" + "не найдена" + informationModel.getId());
        }

        usersInformation.setFullName(informationModel.getFullName());
        usersInformation.setBirthDay(informationModel.getBirthday());
        usersInformation.setPhoneNumber(informationModel.getPhoneNumber());
        usersInformation.setUsers(informationModel.getUsers());

        usersInformation = informationRepository.save(usersInformation);

        //Если id не нулл после обновляения то можем считать что update прошел успешно в бд.
        return usersInformation.getId() != null;
    }

    @Override
    public void deleteUserInfoById(Long id) {
        informationRepository.deleteById(id);
    }
}
