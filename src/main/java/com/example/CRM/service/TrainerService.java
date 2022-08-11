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

    User getTrainerByEmail(String email);

    List<UserModel> getAllTrainers();

    User getTrainerByUserName(String username);



    UpdateUserModel updateTrainer(UpdateUserModel updateUserModel);



    User deleteTrainerByUserName(String username);

    UserModel deleteTrainerById(Long id);
}
