package com.example.CRM.service.impl;

import com.example.CRM.entity.Role;
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
    public CreateRoleModel addNewRole(CreateRoleModel roleModel) {
        Role role = new Role();
        role.setCreateDate(roleModel.getCreateDate());
        role.setActive(true);
        role.setRolesEnum(roleModel.getRoleName());
        roleRepository.save(role);
        return roleModel;
    }

    @Override
    public Role setInActiveRole(Role role, Long status) {
        role.setActive(true);
        return roleRepository.save(role);
    }

    @Override
    public RoleModel getRoleById(Long id) {
        Role role = roleRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Роль связанный с идентификатором " + id + " не найден"));
        return role.toModel();
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleRepository.findByRolesEnum(roleName)
                .orElseThrow(() -> new NotFoundException("Роль не найден"));
    }



    @Override
    public List<RoleModel> getAllRole() {
        return roleRepository
                .findAll()
                .stream()
                .map(Role::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public UpdateRoleModel updateRole(UpdateRoleModel roleModel) {
        if (roleModel == null){
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        }else if (roleModel.getId() == null){
            throw new InvalidParameterException("Id роли не может иметь пустое значени");
        }

        Role role = roleRepository.getById(roleModel.getId());
        if (role == null) {
            throw new UserNotFoundException
                    ("Роль по id не найден " + roleModel.getId());
        }

        role.setRolesEnum(roleModel.getRoleName());
        role.setCreateDate(roleModel.getCreateDate());

        role = roleRepository.save(role);

        return roleModel;
    }

    @Override
    public RoleModel deleteRoleById(Long id) {
        Role role = getById(id);
        Role deleteRole = setInActiveRole(role, -1L);
        return deleteRole.toModel();
    }


    public Role getById(Long id) {
        return roleRepository
                .findById(id)
                .orElseThrow(() -> new
                        NotFoundException
                        ("Роль связанный с идентификатором " + id + " не найдено "));
    }
}
