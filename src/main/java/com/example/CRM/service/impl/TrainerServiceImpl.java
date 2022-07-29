package com.example.CRM.service.impl;

import com.example.CRM.entity.Trainer;
import com.example.CRM.model.TrainerModel;
import com.example.CRM.repository.TrainerRepository;
import com.example.CRM.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerRepository repository;

    @Override
    public Trainer save(Trainer trainer) {
        return null;
    }

    @Override
    public Trainer getById(Long id) {
        return null;
    }

    @Override
    public List<Trainer> getAll() {
        return null;
    }

    @Override
    public TrainerModel createNameTrainer(TrainerModel trainerModel) {
        return null;
    }

    @Override
    public TrainerModel getTrainerById(Long id) {
        return null;
    }

    @Override
    public List<TrainerModel> getAllTrainer() {
        return null;
    }

    @Override
    public void deleteTrainer() {

    }
}
