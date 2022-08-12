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

    UserModel getClientByEmail(User email);

    UserModel getClientByUserName(User username);

    List<UserModel> getAllClients();

    UpdateUserModel updateClient(UpdateUserModel updateUserModel);

    UserModel deleteClientByUserName(User username);

    UserModel deleteClientById(Long id);

    String login(AuthModel authModel);
}
