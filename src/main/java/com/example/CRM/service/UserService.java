package com.example.CRM.service;

import com.example.CRM.entity.Role;
import com.example.CRM.entity.User;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UserModel;

import java.util.List;

public interface UserService {
    CreateUserModel addNewClient(CreateUserModel createUserModel, Role role);

    CreateUserModel addNewTrainer(CreateUserModel createUserModel, Role role);

    CreateUserModel addNewManager(CreateUserModel createUserModel, Role role );

    CreateUserModel addNewAdmin(CreateUserModel createUserModel, Role role);

    User setInActiveUser(User user, Long status);

    UserModel getUserById(Long id);

    UserModel getTrainerById(Long id);

    UserModel getManagerById(Long id);

    UserModel getAdminById(Long id);

    User getUserByUserName(String username);

    List<UserModel> getAllUsers();

    UserModel deleteUserById(Long id);
}
