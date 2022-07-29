package com.example.CRM.service;

import com.example.CRM.entity.UsersInformation;
import com.example.CRM.model.UserInformationModel;

import java.util.List;

public interface UsersInformationService extends BaseService<UsersInformation>{
    UserInformationModel addUserInfo(UserInformationModel informationModel);

    UsersInformation getUserInfoById(Long id);

    List<UsersInformation> getAllUserInfo(UserInformationModel informationModel);

    boolean updateUserInfo(UserInformationModel informationModel);

    void deleteUserInfoById(Long id);
}
