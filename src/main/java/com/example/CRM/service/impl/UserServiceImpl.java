package com.example.CRM.service.impl;

import com.example.CRM.entity.Role;
import com.example.CRM.entity.Users;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.UsersModel;
import com.example.CRM.repository.UsersRepository;
import com.example.CRM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository repository;

    @Override
    public UsersModel addNewUser(UsersModel usersModel) {
        Users users = new Users();
        List<Role> roles = users.getRole();
        users.setRole(roles);
        //Получить из бд роли по usersModel.roles
        //засетить этот список в users.setRole()
        users.setId(usersModel.getId());
        users.setCreateDate(usersModel.getCreateDate());
        users.setEmail(usersModel.getEmail());
        users.setPassword(usersModel.getPassword());
        users.setActive(true);
        repository.save(users);
        return usersModel;
    }

    @Override
    public Users getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Пользователь связонный с id " + id + " не найден"));
    }

    @Override
    public List<Users> getAllUsers() {
        return repository.findAll();
    }


    @Override
    public void deleteUserById(Long id) {
        Users users = new Users();
        users.getId();
        users.setActive(false);
        repository.save(users);
    }
}
