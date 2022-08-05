package com.example.CRM.service;

import com.example.CRM.entity.Trainer;

import com.example.CRM.model.*;

import java.util.List;

public interface TrainerService {

    TrainerModel addNewTrainer(TrainerModel trainerModel);

    Trainer setInActiveTrainer(Trainer trainer, Long status);

    TrainerModel getTrainerById(Long id);

    List<TrainerModel> getAllTrainer();

    TrainerModel deleteTrainerById(Long id);
}
