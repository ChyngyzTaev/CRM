package com.example.CRM.service.impl;

import com.example.CRM.convert.BaseConvert;
import com.example.CRM.entity.Trainer;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.TrainerModel;
import com.example.CRM.repository.TrainerRepository;
import com.example.CRM.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerRepository repository;

    @Autowired
    private BaseConvert<TrainerModel, Trainer> convert;

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
    public Trainer setInActiveUser(Trainer trainer, Long status) {
        trainer.setActive(true);
        return repository.save(trainer);
    }

    @Override
    public TrainerModel getTrainerById(Long id) {
        return convert.convertFromEntity(getById(id));
    }

    @Override
    public List<TrainerModel> getAllTrainer() {
        return getAll()
                .stream()
                .map(convert::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TrainerModel deleteTrainerById(Long id) {
        Trainer trainer = getById(id);
        Trainer deleteTrainer = setInActiveUser(trainer, -1L);
        return convert.convertFromEntity(deleteTrainer);
    }

    @Override
    public Trainer save(Trainer trainer) {
        return repository.save(trainer);
    }

    @Override
    public Trainer getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException("id связанный с идентификатором " + id + " не найдено"));
    }

    @Override
    public List<Trainer> getAll() {
        return repository.findAll();
    }
}
