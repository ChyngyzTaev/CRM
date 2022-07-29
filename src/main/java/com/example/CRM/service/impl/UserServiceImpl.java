package com.example.CRM.service.impl;

import com.example.CRM.convert.BaseConvert;
import com.example.CRM.entity.Role;
import com.example.CRM.entity.User;
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

    @Autowired
    private BaseConvert<UserModel, User> convert;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }


    @Override
    public UserModel addNewUser(UserModel userModel) {
        User user = new User();
        user.setId(userModel.getId());
        user.setCreateDate(userModel.getCreateDate());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
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
        return convert
                .convertFromEntity(getById(id));
    }

    @Override
    public List<UserModel> getAllUsers() {
        return getAll()
                .stream()
                .map(convert::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserModel deleteUserById (Long id){
        User user = getById(id);
        User deleteUSer =setInActiveUser(user, -1L);
        return convert.convertFromEntity(deleteUSer);
    }


    @Override
    public User save(User user) {
        user.setEmail(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        repository.save(user);

        roleService.save(Role.builder()
                .roleName("ROLE_USER")
                .build());
        return user;
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}
