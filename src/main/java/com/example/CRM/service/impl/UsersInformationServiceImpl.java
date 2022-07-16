package com.example.CRM.service.impl;

import com.example.CRM.entity.UsersInformation;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.UsersInformationModel;
import com.example.CRM.repository.UsersInformationRepository;
import com.example.CRM.service.UsersInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class UsersInformationServiceImpl implements UsersInformationService {

    @Autowired
    private UsersInformationRepository repository;

    @Override
    public UsersInformationModel addUserInfo(UsersInformationModel informationModel) {
        UsersInformation usersInformation = new UsersInformation();
        informationModel.setFullName(usersInformation.getFullName());
        informationModel.setBirthday(usersInformation.getBirthDay());
        informationModel.setPhoneNumber(usersInformation.getPhoneNumber());
        informationModel.setUsers(usersInformation.getUsers());
        repository.save(usersInformation);
        return informationModel;
    }

    @Override
    public UsersInformation getUserInfoById(Long id) {
        return repository.findById(id).
                orElseThrow(() ->
                        new NotFoundException("Информация о пользователе связанный с id " + id + " не найдено"));
    }

    @Override
    public List<UsersInformation> getAllUserInfo(UsersInformationModel informationModel) {
        return repository.findAll();
    }

    @Override
    public boolean updateUserInfo(UsersInformationModel informationModel) {

        //Валидация @Valid
        if (informationModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (informationModel.getId() == null) {
            throw new InvalidParameterException("Id информации не может быть пустым");
        }

        //Вытаскиваем из бд и проверяем существует ли такая запись по id
        UsersInformation usersInformation = repository.getById(informationModel.getId());
        if (usersInformation == null) {
            throw new UserNotFoundException
                    ("Информация о пользоваетеле по id не найдена " + informationModel.getId());
        }

        //Перемаппить модельки(свежие данные) на энтити(старые данные), кроме АЙДИ
        usersInformation.setFullName(informationModel.getFullName());
        usersInformation.setBirthDay(informationModel.getBirthday());
        usersInformation.setPhoneNumber(informationModel.getPhoneNumber());
        usersInformation.setUsers(informationModel.getUsers());

        //Сохранить в бд
        usersInformation = repository.save(usersInformation);

        return usersInformation.getId() != null;
    }

    @Override
    public void deleteUserInfoById(Long id) {
        repository.deleteById(id);
    }
}
