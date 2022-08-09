package com.example.CRM.service.impl;

import com.example.CRM.entity.Role;
import com.example.CRM.entity.User;
import com.example.CRM.enums.RolesEnum;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UserModel;
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
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    RolesEnum rolesEnum;

    @Override
    public CreateUserModel addNewUser(CreateUserModel createUserModel) {
        User user = initClient(createUserModel, new Role("Client", RolesEnum.CLIENT));
        userRepository.save(user);
        return createUserModel;
    }

    @Override
    public CreateUserModel addNewTrainer(CreateUserModel createUserModel) {
        User trainer = initTrainer(createUserModel, new Role("Trainer", RolesEnum.TRAINER));
        userRepository.save(trainer);
        return createUserModel;
    }

    @Override
    public CreateUserModel addNewManager(CreateUserModel createUserModel ) {
        User manager = initManager(createUserModel, new Role("Manager", RolesEnum.MANAGER));
        userRepository.save(manager);
        return createUserModel;
    }

    @Override
    public CreateUserModel addNewAdmin(CreateUserModel createUserModel) {
        User admin = initAdmin(createUserModel, new Role("Admin", RolesEnum.ADMIN));
        userRepository.save(admin);
        return createUserModel;
    }

    @Override
    public User setInActiveUser(User user, Long status) {
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public UserModel getUserById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Клиент связанный с идентификатором " + id + " не найден"));
        return user.toModel();
    }

    @Override
    public UserModel getTrainerById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Тренер связанный с идентификатором " + id + " не найден"));
        return user.toModel();
    }

    @Override
    public UserModel getManagerById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Менеджер связанный с идентификатором " + id + " не найден"));
        return user.toModel();
    }

    @Override
    public UserModel getAdminById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Админ связанный с идентификатором " + id + " не найден"));
        return user.toModel();
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }


    @Override
    public List<UserModel> getAllUsers() {
        return userRepository
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
        return userRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException
                                ("Пользоватлеь связанный с идентификатором " + id + " не найдено"));
    }

    private User initClient(CreateUserModel createClientModel, Role role) {
        User client = new User();
        role.setRolesEnum(rolesEnum);
        client.setEmail(createClientModel.getEmail());
        client.setFullName(createClientModel.getFullName());
        client.setUsername(createClientModel.getUsername());
        client.setBirthDay(createClientModel.getBirthday());
        client.setPhoneNumber(createClientModel.getPhoneNumber());
        client.setAge(createClientModel.getAge());
        client.setRole(null);
        client.setActive(true);
        client.setPassword(passwordEncoder.encode(createClientModel.getPassword()));
        return client;
    }

    private User initTrainer(CreateUserModel createTrainerModel, Role role) {
        User trainer = new User();
        role.setRolesEnum(rolesEnum);
        trainer.setEmail(createTrainerModel.getEmail());
        trainer.setFullName(createTrainerModel.getFullName());
        trainer.setUsername(createTrainerModel.getUsername());
        trainer.setBirthDay(createTrainerModel.getBirthday());
        trainer.setPhoneNumber(createTrainerModel.getPhoneNumber());
        trainer.setAge(createTrainerModel.getAge());
        trainer.setRole(null);
        trainer.setActive(true);
        trainer.setPassword(passwordEncoder.encode(createTrainerModel.getPassword()));
        return trainer;
    }


    private User initManager(CreateUserModel createManagerModel, Role role) {
        User manager = new User();
        role.setRolesEnum(rolesEnum);
        manager.setEmail(createManagerModel.getEmail());
        manager.setFullName(createManagerModel.getFullName());
        manager.setUsername(createManagerModel.getUsername());
        manager.setBirthDay(createManagerModel.getBirthday());
        manager.setPhoneNumber(createManagerModel.getPhoneNumber());
        manager.setAge(createManagerModel.getAge());
        manager.setRole(null);
        manager.setActive(true);
        manager.setPassword(passwordEncoder.encode(createManagerModel.getPassword()));
        return manager;
    }


    private User initAdmin(CreateUserModel createAdminModel, Role role) {
        User admin = new User();
        role.setRolesEnum(rolesEnum);
        admin.setEmail(createAdminModel.getEmail());
        admin.setFullName(createAdminModel.getFullName());
        admin.setBirthDay(createAdminModel.getBirthday());
        admin.setPhoneNumber(createAdminModel.getPhoneNumber());
        admin.setUsername(createAdminModel.getUsername());
        admin.setAge(createAdminModel.getAge());
        admin.setRole(null);
        admin.setActive(true);
        admin.setPassword(passwordEncoder.encode(createAdminModel.getPassword()));
        return admin;
    }
}
