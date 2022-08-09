package com.example.CRM.service;

import com.example.CRM.entity.Role;
import com.example.CRM.entity.User;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UserModel;

import java.util.List;

public interface UserService {
    CreateUserModel addNewUser(CreateUserModel createUserModel);

    CreateUserModel addNewTrainer(CreateUserModel createUserModel);

    CreateUserModel addNewManager(CreateUserModel createUserModel );

    CreateUserModel addNewAdmin(CreateUserModel createUserModel);

    User setInActiveUser(User user, Long status);

    UserModel getUserById(Long id);

    UserModel getTrainerById(Long id);

    UserModel getManagerById(Long id);

    UserModel getAdminById(Long id);

    User getUserByUserName(String username);

    List<UserModel> getAllUsers();

    UserModel deleteUserById(Long id);
}
