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

    UserModel getManagerByEmail(String email);

    UserModel getManagerByUserName(String username);

    List<UserModel> getAllManagers();

    boolean updateManager(UpdateUserModel updateUserModel);

    UserModel deleteManagerByUserName(String username);

    UserModel deleteManagerById(Long id);
}
