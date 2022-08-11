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

    User getClientByEmail(String email);

    User getClientByUserName(String username);

    List<UserModel> getAllClients();

    UpdateUserModel updateClient(UpdateUserModel updateUserModel);

    User deleteClientByUserName(String username);

    UserModel deleteClientById(Long id);

    String login(AuthModel authModel);
}
