package com.example.CRM.service;

import com.example.CRM.entity.User;
import com.example.CRM.model.UserModel;

import java.util.List;

public interface UserService extends BaseService<User> {
    UserModel addNewUser(UserModel userModel);

    User setInActiveUser(User user, Long status);

    UserModel getUserById(Long id);

    List<UserModel> getAllUsers();

    UserModel deleteUserById(Long id);
}
