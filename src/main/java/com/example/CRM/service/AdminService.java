package com.example.CRM.service;

import com.example.CRM.entity.User;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UpdateUserModel;
import com.example.CRM.model.user.UserModel;

import java.util.List;

public interface AdminService {
    UserModel addNewAdmin(CreateUserModel createUserModel);

    User setInActiveAdmin(User user, Long status);

    UserModel getAdminById(Long id);

    User getAdminByEmail(String email);

    User getAdminByUserName(String username);

    List<UserModel> getAllAdmins();

    UpdateUserModel updateAdmin(UpdateUserModel updateUserModel);

    User deleteAdminByUserName(String username);

    UserModel deleteAdminById(Long id);
}
