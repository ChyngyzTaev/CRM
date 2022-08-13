package com.example.CRM.service;

import com.example.CRM.entity.User;
import com.example.CRM.model.user.AuthModel;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UpdateUserModel;
import com.example.CRM.model.user.UserModel;

import java.util.List;

public interface UserService {
    UserModel addNewClient(CreateUserModel createUserModel);

    User setInActiveClient(User user, Long status);

    UserModel getClientById(Long id);

    UserModel getClientByEmail(String email);

    UserModel getClientByUserName(String username);

    List<UserModel> getAllClients();

    boolean updateClient(UpdateUserModel updateUserModel);

    UserModel deleteClientByUserName(String username);

    UserModel deleteClientById(Long id);

    String login(AuthModel authModel);
}
