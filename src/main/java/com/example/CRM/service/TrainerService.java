package com.example.CRM.service;

import com.example.CRM.entity.User;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UpdateUserModel;
import com.example.CRM.model.user.UserModel;

import java.util.List;

public interface TrainerService {
    UserModel addNewTrainer(CreateUserModel createUserModel);

    User setInActiveTrainer(User user, Long status);

    UserModel getTrainerById(Long id);

    List<UserModel> getAllTrainers();

    UserModel getTrainerByUserName(User username);

    UserModel getTrainerByEmail(User email);

    UpdateUserModel updateTrainer(UpdateUserModel updateUserModel);

    UserModel deleteTrainerByUserName(User username);

    UserModel deleteTrainerById(Long id);
}
