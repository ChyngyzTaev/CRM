package com.example.CRM.service;

import com.example.CRM.entity.UsersInformation;
import com.example.CRM.model.UsersInformationModel;

import java.util.List;

public interface UsersInformationService {
    UsersInformationModel addUserInfo(UsersInformationModel informationModel);

    UsersInformation getUserInfoById(Long id);

    List<UsersInformation> getAllUserInfo(UsersInformationModel informationModel);

    boolean updateUserInfo(UsersInformationModel informationModel);

    void deleteUserInfoById(Long id);
}
