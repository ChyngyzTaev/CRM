package com.example.CRM.service.impl;

import com.example.CRM.convert.BaseConvert;
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
import java.util.stream.Collectors;

@Service
public class UsersInformationServiceImpl implements UsersInformationService {

    @Autowired
    private UsersInformationRepository repository;

    @Autowired
    private BaseConvert<UsersInformationModel, UsersInformation> convert;

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
        return convert.convertFromEntity(getById(id));
    }

    @Override
    public List<UsersInformationModel> getAllUserInfo() {
        return getAll()
                .stream()
                .map(convert::convertFromEntity)
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
        return convert.convertFromEntity(deleteUSer);
    }

    @Override
    public UsersInformation save(UsersInformation usersInformation) {
        return repository.save(usersInformation);
    }

    @Override
    public UsersInformation getById(Long id) {
        return repository
                .findById(id).orElseThrow(()->
                        new NotFoundException("Информация о пользователе связанный с идентификатором "
                                + id + " не найдено"));
    }

    @Override
    public List<UsersInformation> getAll() {
        return repository.findAll();
    }
}
