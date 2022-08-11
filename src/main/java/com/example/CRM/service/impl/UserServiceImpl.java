package com.example.CRM.service.impl;

import com.example.CRM.entity.User;
import com.example.CRM.enums.RolesEnum;
import com.example.CRM.exception.ApiFailException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.user.AuthModel;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UpdateUserModel;
import com.example.CRM.model.user.UserModel;
import com.example.CRM.repository.UserRepository;
import com.example.CRM.service.RoleService;
import com.example.CRM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private  PasswordEncoder passwordEncoder;


    RolesEnum rolesEnum;

    @Override
    public UserModel addNewClient(CreateUserModel createUserModel) {
        validateVariablesForNull(createUserModel);
        User user = createUserModel.toUser();
        userRepository.save(user);
        return user.toModel();
    }

    @Override
    public User setInActiveClient(User user, Long status) {
        user.setIsActive(user.getIsActive());
        return userRepository.save(user);
    }

    @Override
    public UserModel getClientById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Клиент связанный с идентификатором " + id + " не найден"));
        return user.toModel();
    }

    @Override
    public User getClientByEmail(String email) {
        return userRepository
                .findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Клиент связанный с таким email не найден."));
    }


    @Override
    public User getClientByUserName(String username) {
        return userRepository
                .findClientByUsername(username)
                .orElseThrow(() -> new NotFoundException("Клиент не найден"));
    }


    @Override
    public List<UserModel> getAllClients() {
        return userRepository
                .findAll()
                .stream()
                .map(User::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public UpdateUserModel updateClient(UpdateUserModel updateUserModel) {
        if (updateUserModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (updateUserModel.getId() == null) {
            throw new InvalidParameterException("Id абонемента  не может иметь пустое значени");
        }

        User user = userRepository.getById(updateUserModel.getId());
        if (user == null) {
            throw new UserNotFoundException
                    ("Клиент по id не нанйдено " + updateUserModel.getId());
        }

        user.setFullName(updateUserModel.getFullName());
        user.setUsername(updateUserModel.getUsername());
        user.setBirthDay(updateUserModel.getBirthday());
        user.setEmail(updateUserModel.getEmail());
        user.setPassword(updateUserModel.getPassword());
        user.setPhoneNumber(updateUserModel.getPhoneNumber());
        user.setCreateDate(updateUserModel.getCreateDate());

        user = userRepository.save(user);

        return updateUserModel;
    }

    @Override
    public User deleteClientByUserName(String username) {
       return userRepository.deleteByUsername(username)
               .orElseThrow(() -> new ApiFailException("Удаление не выполнено, возможно ошибка в username"));
    }

    @Override
    public UserModel deleteClientById(Long id){
        User user = getById(id);
        User deleteClient = setInActiveClient(user, -1L);
        return deleteClient.toModel();
    }

    @Override
    public String login(AuthModel authModel) {
        return null;
    }


    public User getById(Long id) {
        return userRepository
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
