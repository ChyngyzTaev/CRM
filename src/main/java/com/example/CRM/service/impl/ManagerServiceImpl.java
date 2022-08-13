package com.example.CRM.service.impl;

import com.example.CRM.entity.User;
import com.example.CRM.exception.ApiFailException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UpdateUserModel;
import com.example.CRM.model.user.UserModel;
import com.example.CRM.repository.UserRepository;
import com.example.CRM.service.ManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private UserRepository managerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel addNewManager(CreateUserModel createUserModel) {
        validateVariablesForNull(createUserModel);
        User manager = createUserModel.toUser();
        managerRepository.save(manager);
        return manager.toModel();
    }

    @Override
    public User setInActiveManager(User user, Long status) {
        user.setIsActive(user.getIsActive());
        return managerRepository.save(user);
    }

    @Override
    public UserModel getManagerById(Long id) {
        User user = managerRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Менеджер связанный с идентификатором " + id + " не найден"));
        return user.toModel();
    }

    @Override
    public UserModel getManagerByEmail(String email) {
        User manager = managerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException
                        ("Информация о клиенте связанная с " + email + "не найдена"));
        return manager.toModel();
    }

    @Override
    public UserModel getManagerByUserName(String username) {
        User manager = managerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException
                        ("Информация о клиенте связанная с " + username + "не найдена"));
        return manager.toModel();
    }

    @Override
    public List<UserModel> getAllManagers() {
        return managerRepository
                .findAll()
                .stream()
                .map(User::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateManager(UpdateUserModel updateUserModel) {
        if (updateUserModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (updateUserModel.getId() == null) {
            throw new InvalidParameterException("Id абонемента  не может иметь пустое значени");
        }

        User manager = managerRepository.getById(updateUserModel.getId());
        if (manager == null) {
            throw new UserNotFoundException
                    ("Менеджер по id не нанйдено " + updateUserModel.getId());
        }

        manager.setFullName(updateUserModel.getFullName());
        manager.setUsername(updateUserModel.getUsername());
        manager.setBirthDay(updateUserModel.getBirthday());
        manager.setEmail(updateUserModel.getEmail());
        manager.setPassword(updateUserModel.getPassword());
        manager.setPhoneNumber(updateUserModel.getPhoneNumber());
        manager.setCreateDate(updateUserModel.getCreateDate());

        manager = managerRepository.save(manager);

        return manager.getId() != null;
    }

    @Override
    public UserModel deleteManagerByUserName(String username) {
        User client = managerRepository.findByEmail(username)
                .orElseThrow(() -> new ApiFailException("Ошибка при удалении пользователя"));
        managerRepository.delete(client);
        return client.toModel();
    }

    @Override
    public UserModel deleteManagerById(Long id) {
        User manager = getById(id);
        User deleteManager = setInActiveManager(manager, -1L);
        return deleteManager.toModel();
    }

    public User getById(Long id) {
        return managerRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException
                                ("Пользоватлеь связанный с идентификатором " + id + " не найдено"));
    }


    private void validateVariablesForNull(CreateUserModel createUserModel) {
        if (createUserModel.getRolesEnum() == null)
            throw new ApiFailException("RoleEnum не заполнен");
        if (createUserModel.getFullName() == null)
            throw new ApiFailException("Full name не заполнен");
        if (createUserModel.getUsername() == null)
            throw new ApiFailException("Username не заполнен");
        if (createUserModel.getEmail() == null)
            throw new ApiFailException("Email не заполнен");
        if (createUserModel.getPassword() == null)
            throw new ApiFailException("Password не заполнен");
    }
}
