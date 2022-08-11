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
    public User getManagerByEmail(String email) {
        return managerRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Менеджер связанный с таким email не найден."));
    }

    @Override
    public User getManagerByUserName(String username) {
        return managerRepository
                .findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Менеджер не найден"));
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
    public UpdateUserModel updateManager(UpdateUserModel updateUserModel) {
        if (updateUserModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (updateUserModel.getId() == null) {
            throw new InvalidParameterException("Id абонемента  не может иметь пустое значени");
        }

        User user = managerRepository.getById(updateUserModel.getId());
        if (user == null) {
            throw new UserNotFoundException
                    ("Менеджер по id не нанйдено " + updateUserModel.getId());
        }

        user.setFullName(updateUserModel.getFullName());
        user.setUsername(updateUserModel.getUsername());
        user.setBirthDay(updateUserModel.getBirthday());
        user.setEmail(updateUserModel.getEmail());
        user.setPassword(updateUserModel.getPassword());
        user.setPhoneNumber(updateUserModel.getPhoneNumber());
        user.setCreateDate(updateUserModel.getCreateDate());

        user = managerRepository.save(user);

        return updateUserModel;
    }

    @Override
    public User deleteManagerByUserName(String username) {
        User manager = getManagerByUserName(username);
        managerRepository.delete(manager);
        return manager;
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
