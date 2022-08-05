package com.example.CRM.service.impl;

import com.example.CRM.entity.Trainer;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.TrainerModel;
import com.example.CRM.repository.TrainerRepository;
import com.example.CRM.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerRepository repository;


    @Override
    public TrainerModel addNewTrainer(TrainerModel trainerModel) {
        Trainer trainer = new Trainer();
        trainer.setId(trainerModel.getId());
        trainer.setCreateDate(trainerModel.getCreateDate());
        trainer.setFullName(trainerModel.getFullName());
        trainer.setAge(trainerModel.getAge());
        trainer.setActive(true);
        repository.save(trainer);
        return trainerModel;
    }

    @Override
    public Trainer setInActiveTrainer(Trainer trainer, Long status) {
        trainer.setActive(true);
        return repository.save(trainer);
    }

    @Override
    public TrainerModel getTrainerById(Long id) {
        Trainer trainer = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Тренер связанный с идентификатором " + id + " не найден"));
        return trainer.toModel();
    }

    @Override
    public List<TrainerModel> getAllTrainer() {
        return repository
                .findAll()
                .stream()
                .map(Trainer::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public TrainerModel deleteTrainerById(Long id) {
        Trainer trainer = getById(id);
        Trainer deleteTrainer = setInActiveTrainer(trainer, -1L);
        return deleteTrainer.toModel();
    }

    public Trainer getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException
                                ("Тренер связанный с идентификатором " + id + " не найдено"));
    }
}
