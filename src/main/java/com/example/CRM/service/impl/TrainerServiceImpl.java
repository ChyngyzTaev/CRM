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
    public List<UserModel> getAllTrainers() {
        return trainerRepository
                .findAll()
                .stream()
                .map(User::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserModel getTrainerByUserName(String username) {
        User trainer = trainerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException
                        ("Информация о клиенте связанная с " + username + "не найдена"));
        return trainer.toModel();
    }

    @Override
    public UserModel getTrainerByEmail(String email) {
        User trainer = trainerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException
                        ("Информация о клиенте связанная с " + email + "не найдена"));
        return trainer.toModel();
    }

    @Override
    public boolean updateTrainer(UpdateUserModel updateUserModel) {
        if (updateUserModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (updateUserModel.getId() == null) {
            throw new InvalidParameterException("Id абонемента  не может иметь пустое значени");
        }
        User trainer = trainerRepository.getById(updateUserModel.getId());
        if (trainer == null) {
            throw new UserNotFoundException
                    ("Тренер по id не нанйдено " + updateUserModel.getId());
        }
        trainer.setFullName(updateUserModel.getFullName());
        trainer.setUsername(updateUserModel.getUsername());
        trainer.setBirthDay(updateUserModel.getBirthday());
        trainer.setEmail(updateUserModel.getEmail());
        trainer.setPassword(updateUserModel.getPassword());
        trainer.setPhoneNumber(updateUserModel.getPhoneNumber());
        trainer.setCreateDate(updateUserModel.getCreateDate());
        trainer = trainerRepository.save(trainer);
        return trainer.getId() != null;
    }

    @Override
    public UserModel deleteTrainerByUserName(String username) {
        User client = trainerRepository.findByEmail(username)
                .orElseThrow(() -> new ApiFailException("Ошибка при удалении пользователя"));
        trainerRepository.delete(client);
        return client.toModel();
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
