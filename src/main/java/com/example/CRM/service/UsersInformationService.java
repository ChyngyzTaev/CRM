package com.example.CRM.service;

import com.example.CRM.entity.UsersInformation;
import com.example.CRM.model.UserInformationModel;

import java.util.List;

public interface UsersInformationService extends BaseService<UsersInformation>{
    UserInformationModel addUserInfo(UserInformationModel informationModel);

    UsersInformation setInActiveUser(UsersInformation information, Long status);

    UserInformationModel getUserInfoById(Long id);

    List<UserInformationModel> getAllUserInfo();

    UserInformationModel updateUserInfo(UserInformationModel informationModel);

    UserInformationModel deleteUserInfoById(Long id);
}
