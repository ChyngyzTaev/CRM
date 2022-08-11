//package com.example.CRM.boot;
//
//import com.example.CRM.entity.User;
//import com.example.CRM.entity.UserRole;
//import com.example.CRM.enums.RolesEnum;
//import com.example.CRM.repository.RoleRepository;
//import com.example.CRM.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class ApplicationStartRunner implements CommandLineRunner {
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        User admin = new User();
//        admin.setUsername("admin");
//        admin.setFullName("adminov admin adminovich");
//        String adminPassword = passwordEncoder.encode("admin");
//        admin.setPassword(adminPassword);
//        admin.setIsActive(1L);
//
//        User manager = new User();
//        manager.setUsername("manager");
//        manager.setFullName("managerov manager managerovich");
//        String managerPassword = passwordEncoder.encode("manager");
//        manager.setPassword(managerPassword);
//        manager.setIsActive(1L);
//
//        User trainer = new User();
//        trainer.setUsername("trainer");
//        trainer.setFullName("trainerov trainer trainerovich");
//        String trainerPassword = passwordEncoder.encode("trainer");
//        trainer.setPassword(trainerPassword);
//        trainer.setIsActive(1L);
//
//        User client = new User();
//        client.setUsername("client");
//        client.setFullName("clientov client clientovich");
//        String clientPassword = passwordEncoder.encode("client");
//        client.setPassword(clientPassword);
//        client.setIsActive(1L);
//
//
//        List<User> users = new ArrayList<>();
//        users.add(admin);
//        users.add(manager);
//        users.add(trainer);
//        users.add(client);
//
//        userRepository.saveAll(users);
//
//        UserRole roleAdmin = new UserRole();
//        roleAdmin.setRoleName(RolesEnum.ADMIN);
//        roleAdmin.setUser(admin);
//
//        UserRole roleManger = new UserRole();
//        roleManger.setRoleName(RolesEnum.MANAGER);
//        roleManger.setUser(manager);
//
//        UserRole roleTrainer = new UserRole();
//        roleTrainer.setRoleName(RolesEnum.TRAINER);
//        roleTrainer.setUser(trainer);
//
//        UserRole roleUser = new UserRole();
//        roleUser.setRoleName(RolesEnum.CLIENT);
//        roleUser.setUser(client);
//        roleAdmin.setIsActive(1L);
//        roleManger.setIsActive(1L);
//        roleTrainer.setIsActive(1L);
//        roleUser.setIsActive(1L);
//
//        List<UserRole> userRoles = new ArrayList<>();
//        userRoles.add(roleUser);
//        userRoles.add(roleAdmin);
//        userRoles.add(roleManger);
//        userRoles.add(roleTrainer);
//        roleRepository.saveAll(userRoles);
//    }
//}
