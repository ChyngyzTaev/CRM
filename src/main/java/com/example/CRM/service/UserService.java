package com.example.CRM.service;

import com.example.CRM.entity.Users;
import com.example.CRM.model.UsersModel;

import java.util.List;

public interface UserService {
    UsersModel addNewUser(UsersModel usersModel);

    Users getUserById(Long id);

    List<Users> getAllUsers();

    void deleteUserById(Long id);
}
