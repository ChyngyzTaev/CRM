package com.example.CRM.service;

import com.example.CRM.entity.User;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UpdateUserModel;
import com.example.CRM.model.user.UserModel;

import java.util.List;

public interface ManagerService {

    UserModel addNewManager(CreateUserModel createUserModel );

    User setInActiveManager(User user, Long status);

    UserModel getManagerById(Long id);

    User getManagerByEmail(String email);

    User getManagerByUserName(String username);

    List<UserModel> getAllManagers();

    UpdateUserModel updateManager(UpdateUserModel updateUserModel);

    User deleteManagerByUserName(String username);

    UserModel deleteManagerById(Long id);
}
