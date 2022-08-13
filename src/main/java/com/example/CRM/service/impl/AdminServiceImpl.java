package com.example.CRM.service.impl;

import com.example.CRM.entity.User;
import com.example.CRM.exception.ApiFailException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UpdateUserModel;
import com.example.CRM.model.user.UserModel;
import com.example.CRM.repository.UserRepository;
import com.example.CRM.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel addNewAdmin(CreateUserModel createUserModel) {
        validateVariablesForNull(createUserModel);
        User admin = createUserModel.toUser();
        adminRepository.save(admin);
        return admin.toModel();
    }

    @Override
    public User setInActiveAdmin(User user, Long status) {
        user.setIsActive(user.getIsActive());
        return adminRepository.save(user);
    }

    @Override
    public UserModel getAdminById(Long id) {
        User user = adminRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Админ связанный с идентификатором " + id + " не найден"));
        return user.toModel();
    }

    @Override
    public UserModel getAdminByEmail(String email) {
        User trainer = adminRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException
                        ("Информация о клиенте связанная с " + email + "не найдена"));
        return trainer.toModel();
    }

    @Override
    public UserModel getAdminByUserName(String username) {
        User trainer = adminRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException
                        ("Информация о клиенте связанная с " + username + "не найдена"));
        return trainer.toModel();
    }

    @Override
    public List<UserModel> getAllAdmins() {
        return adminRepository
                .findAll()
                .stream()
                .map(User::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateAdmin(UpdateUserModel updateUserModel) {
        if (updateUserModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (updateUserModel.getId() == null) {
            throw new InvalidParameterException("Id абонемента  не может иметь пустое значени");
        }

        User admin = adminRepository.getById(updateUserModel.getId());
        if (admin == null) {
            throw new UserNotFoundException
                    ("Клиент по id не нанйдено " + updateUserModel.getId());
        }

        admin.setFullName(updateUserModel.getFullName());
        admin.setUsername(updateUserModel.getUsername());
        admin.setBirthDay(updateUserModel.getBirthday());
        admin.setEmail(updateUserModel.getEmail());
        admin.setPassword(updateUserModel.getPassword());
        admin.setPhoneNumber(updateUserModel.getPhoneNumber());
        admin.setCreateDate(updateUserModel.getCreateDate());

        admin = adminRepository.save(admin);

        return admin.getId() != null;
    }

    @Override
    public UserModel deleteAdminByUserName(String username) {
        User client = adminRepository.findByEmail(username)
                .orElseThrow(() -> new ApiFailException("Ошибка при удалении пользователя"));
        adminRepository.delete(client);
        return client.toModel();
    }

    @Override
    public UserModel deleteAdminById(Long id) {
        User admin = getById(id);
        User deleteAdmin = setInActiveAdmin(admin, -1L);
        return deleteAdmin.toModel();
    }

    public User getById(Long id) {
        return adminRepository
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
