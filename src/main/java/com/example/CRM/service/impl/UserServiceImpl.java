package com.example.CRM.service.impl;

import com.example.CRM.entity.User;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.UserModel;
import com.example.CRM.repository.UserRepository;
import com.example.CRM.service.RoleService;
import com.example.CRM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;


    @Override
    public UserModel addNewUser(UserModel userModel) {
        User user = new User();
        user.setId(userModel.getId());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        user.setCreateDate(userModel.getCreateDate());
        user.setActive(true);
        repository.save(user);
        return userModel;
    }

    @Override
    public User setInActiveUser(User user, Long status) {
        user.setActive(true);
        return repository.save(user);
    }

    @Override
    public UserModel getUserById(Long id) {
        User user = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Пользователь связанный с идентификатором " + id + " не найден"));
        return user.toModel();
    }

    @Override
    public List<UserModel> getAllUsers() {
        return repository
                .findAll()
                .stream()
                .map(User::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserModel deleteUserById (Long id){
        User user = getById(id);
        User deleteUSer =setInActiveUser(user, -1L);
        return deleteUSer.toModel();
    }


    public User getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException
                                ("Пользоватлеь связанный с идентификатором " + id + " не найдено"));
    }
}
