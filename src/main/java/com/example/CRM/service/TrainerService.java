package com.example.CRM.service;

import com.example.CRM.entity.Trainer;

import com.example.CRM.model.TrainerModel;

import java.util.List;

public interface TrainerService extends BaseService<Trainer> {
    TrainerModel addNewTrainer(TrainerModel trainerModel);

    Trainer setInActiveTrainer(Trainer trainer, Long status);

    TrainerModel getTrainerById(Long id);

    List<TrainerModel> getAllTrainer();

    TrainerModel deleteTrainerById(Long id);
}
