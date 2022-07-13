package com.example.CRM.service.impl;

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
    private UsersRepository usersRepository;

    @Override
    public UsersModel addNewUser(UsersModel usersModel) {
        Users users = new Users();
        Long id = usersModel.getId();
        checkIdForNull(id);
        users.setId(usersModel.getId());
        users.setCreateDate(usersModel.getCreateDate());
        users.setEmail(usersModel.getEmail());
        users.setPassword(usersModel.getPassword());
        usersRepository.save(users);
        return usersModel;
    }


    @Override
    public Users getUserById(Long id) {
        return usersRepository
                .findById(id)
                .orElseThrow(()
                        ->
                        new NotFoundException
                                ("Id" + id + "не найден!"));
    }

    @Override
    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }


    @Override
    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }

    public void checkIdForNull(Long id){
        if (id == null){
            System.out.println("Id не может быть пустым!");
        }
    }
}
