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
    public UserModel getAdminByEmail(User email) {
        adminRepository.findByEmail(email.getEmail());
        return email.toModel();
    }

    @Override
    public UserModel getAdminByUserName(User username) {
        adminRepository.findByUsername(username.getUsername());
        return username.toModel();
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
    public UpdateUserModel updateAdmin(UpdateUserModel updateUserModel) {
        if (updateUserModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (updateUserModel.getId() == null) {
            throw new InvalidParameterException("Id абонемента  не может иметь пустое значени");
        }

        User user = adminRepository.getById(updateUserModel.getId());
        if (user == null) {
            throw new UserNotFoundException
                    ("Клиент по id не нанйдено " + updateUserModel.getId());
        }

        user.setFullName(updateUserModel.getFullName());
        user.setUsername(updateUserModel.getUsername());
        user.setBirthDay(updateUserModel.getBirthday());
        user.setEmail(updateUserModel.getEmail());
        user.setPassword(updateUserModel.getPassword());
        user.setPhoneNumber(updateUserModel.getPhoneNumber());
        user.setCreateDate(updateUserModel.getCreateDate());

        user = adminRepository.save(user);

        return updateUserModel;
    }

    @Override
    public UserModel deleteAdminByUserName(User username) {
        adminRepository.delete(username);
        return username.toModel();
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
