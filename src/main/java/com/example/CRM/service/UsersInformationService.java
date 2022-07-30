package com.example.CRM.service;

import com.example.CRM.entity.UsersInformation;
import com.example.CRM.model.UsersInformationModel;

import java.util.List;

public interface UsersInformationService extends BaseService<UsersInformation>{
    UsersInformationModel addUserInfo(UsersInformationModel informationModel);

    UsersInformation setInActiveUserInformation(UsersInformation information, Long status);

    UsersInformationModel getUserInfoById(Long id);

    List<UsersInformationModel> getAllUserInfo();

    UsersInformationModel updateUserInfo(UsersInformationModel informationModel);

    UsersInformationModel deleteUserInfoById(Long id);
}
