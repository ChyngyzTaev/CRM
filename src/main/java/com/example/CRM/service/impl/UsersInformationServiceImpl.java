package com.example.CRM.service.impl;

import com.example.CRM.entity.UsersInformation;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.UsersInformationModel;
import com.example.CRM.repository.UsersInformationRepository;
import com.example.CRM.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersInformationServiceImpl implements UserInformationService {

    @Autowired
    private UsersInformationRepository repository;


    @Override
    public UsersInformationModel addUserInfo(UsersInformationModel informationModel) {
        UsersInformation usersInformation = new UsersInformation();
        usersInformation.setFullName(informationModel.getFullName());
        usersInformation.setBirthDay(informationModel.getBirthday());
        usersInformation.setPhoneNumber(informationModel.getPhoneNumber());
        usersInformation.setCreateDate(informationModel.getCreateDate());
        repository.save(usersInformation);
        return informationModel;
    }

    @Override
    public UsersInformation setInActiveUserInformation(UsersInformation information, Long status) {
        information.setActive(true);
        return repository.save(information);
    }

    @Override
    public UsersInformationModel getUserInfoById(Long id) {
        UsersInformation usersInformation = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Информайия о пользователе связанный с идентификатором " + id + " не найден"));
        return usersInformation.toModel();
    }

    @Override
    public List<UsersInformationModel> getAllUserInfo() {
        return repository
                .findAll()
                .stream()
                .map(UsersInformation:: toModel)
                .collect(Collectors.toList());
    }

    @Override
    public UsersInformationModel updateUserInfo(UsersInformationModel informationModel) {

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

        //Сохранить в бд
        usersInformation = repository.save(usersInformation);

        return informationModel;
    }

    @Override
    public UsersInformationModel deleteUserInfoById(Long id) {
        UsersInformation usersInformation = getById(id);
        UsersInformation deleteUSer = setInActiveUserInformation(usersInformation, -1L);
        return deleteUSer.toModel();
    }


    public UsersInformation getById(Long id) {
        return repository
                .findById(id).orElseThrow(()->
                        new NotFoundException
                                ("Информация о пользователе связанный с идентификатором " + id + " не найдено"));
    }
}
