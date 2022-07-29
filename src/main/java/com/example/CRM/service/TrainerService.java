package com.example.CRM.service;

import com.example.CRM.entity.Trainer;
import com.example.CRM.model.TrainerModel;

import java.util.List;

public interface TrainerService extends BaseService<Trainer> {
    TrainerModel createNameTrainer(TrainerModel trainerModel);

    TrainerModel getTrainerById(Long id);

    List<TrainerModel> getAllTrainer();

    void deleteTrainer();
}
