package com.example.CRM.service.impl;

import com.example.CRM.entity.UserRole;
import com.example.CRM.exception.ApiFailException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.Role.CreateRoleModel;
import com.example.CRM.model.Role.RoleModel;
import com.example.CRM.model.Role.UpdateRoleModel;
import com.example.CRM.repository.RoleRepository;
import com.example.CRM.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleModel addNewRole(CreateRoleModel createRoleModel) {
        validateVariablesForNull(createRoleModel);
        UserRole userRole = createRoleModel.toRole();
        roleRepository.save(userRole);
        return userRole.toModel();
    }

    @Override
    public UserRole setInActiveRole(UserRole userRole, Long status) {
        userRole.setIsActive(userRole.getIsActive());
        return roleRepository.save(userRole);
    }

    @Override
    public RoleModel getRoleById(Long id) {
        UserRole userRole = roleRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Роль связанный с идентификатором " + id + " не найден"));
        return userRole.toModel();
    }

    @Override
    public UserRole getRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new NotFoundException
                        ("Информация связанная с " + roleName + " не найдена"));
    }

    @Override
    public List<RoleModel> getAllRole() {
        return roleRepository
                .findAll()
                .stream()
                .map(UserRole::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateRole(UpdateRoleModel roleModel) {
        if (roleModel == null){
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        }else if (roleModel.getId() == null){
            throw new InvalidParameterException("Id роли не может иметь пустое значени");
        }
        UserRole userRole = roleRepository.getById(roleModel.getId());
        if (userRole == null) {
            throw new UserNotFoundException
                    ("Роль по id не найден " + roleModel.getId());
        }
        userRole.setRoleName(roleModel.getRoleName());
        userRole.setCreateDate(roleModel.getCreateDate());
        userRole = roleRepository.save(userRole);
        return userRole.getId() != null;
    }

    @Override
    public RoleModel deleteRoleById(Long id) {
        UserRole userRole = getById(id);
        UserRole deleteUserRole = setInActiveRole(userRole, -1L);
        return deleteUserRole.toModel();
    }


    public UserRole getById(Long id) {
        return roleRepository
                .findById(id)
                .orElseThrow(() -> new
                        NotFoundException
                        ("Роль связанный с идентификатором " + id + " не найдено "));
    }

    public void validateVariablesForNull(CreateRoleModel createRoleModel){
        if (createRoleModel.getRoleName()== null)
            throw new ApiFailException("RoleEnum не заполнен");
    }
}
