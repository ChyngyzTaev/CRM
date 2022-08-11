package com.example.CRM.service.impl;

import com.example.CRM.entity.User;
import com.example.CRM.exception.ApiFailException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UpdateUserModel;
import com.example.CRM.model.user.UserModel;
import com.example.CRM.repository.UserRepository;
import com.example.CRM.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private UserRepository trainerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserModel addNewTrainer(CreateUserModel createUserModel) {
        validateVariablesForNull(createUserModel);
        User trainer = createUserModel.toUser();
        trainerRepository.save(trainer);
        return trainer.toModel();
    }

    @Override
    public User setInActiveTrainer(User user, Long status) {
        user.setIsActive(user.getIsActive());
        return trainerRepository.save(user);
    }

    @Override
    public UserModel getTrainerById(Long id) {
        User user = trainerRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Тренер связанный с идентификатором " + id + " не найден"));
        return user.toModel();
    }

    @Override
    public User getTrainerByEmail(String email) {
        return trainerRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Клиент связанный с таким email не найден."));
    }

    @Override
    public List<UserModel> getAllTrainers() {
        return trainerRepository
                .findAll()
                .stream()
                .map(User::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public User getTrainerByUserName(String username) {
        return trainerRepository
                .findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Тренер не найден"));
    }

    @Override
    public UpdateUserModel updateTrainer(UpdateUserModel updateUserModel) {
        if (updateUserModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (updateUserModel.getId() == null) {
            throw new InvalidParameterException("Id абонемента  не может иметь пустое значени");
        }

        User user = trainerRepository.getById(updateUserModel.getId());
        if (user == null) {
            throw new UserNotFoundException
                    ("Тренер по id не нанйдено " + updateUserModel.getId());
        }

        user.setFullName(updateUserModel.getFullName());
        user.setUsername(updateUserModel.getUsername());
        user.setBirthDay(updateUserModel.getBirthday());
        user.setEmail(updateUserModel.getEmail());
        user.setPassword(updateUserModel.getPassword());
        user.setPhoneNumber(updateUserModel.getPhoneNumber());
        user.setCreateDate(updateUserModel.getCreateDate());

        user = trainerRepository.save(user);

        return updateUserModel;
    }

    @Override
    public User deleteTrainerByUserName(String username) {
        User trainer = getTrainerByUserName(username);
        trainerRepository.delete(trainer);
        return trainer;
    }

    @Override
    public UserModel deleteTrainerById(Long id) {
        User trainer = getById(id);
        User deleteTrainer = setInActiveTrainer(trainer, -1L);
        return deleteTrainer.toModel();
    }

    public User getById(Long id) {
        return trainerRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException
                                ("Пользоватлеь связанный с идентификатором " + id + " не найдено"));
    }

    private void validateVariablesForNull(CreateUserModel createUserModel) {
        if (createUserModel.getRolesEnum() == null)
            throw new ApiFailException("RoleEnum не заполнен");
        if (createUserModel.getFullName() == null)
            throw new ApiFailException("Full name не заполнен");
        if (createUserModel.getUsername() == null)
            throw new ApiFailException("Username не заполнен");
        if (createUserModel.getEmail() == null)
            throw new ApiFailException("Email не заполнен");
        if (createUserModel.getPassword() == null)
            throw new ApiFailException("Password не заполнен");
    }
}
