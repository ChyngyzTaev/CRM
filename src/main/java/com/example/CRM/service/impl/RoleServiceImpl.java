package com.example.CRM.service.impl;

import com.example.CRM.entity.Role;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.RoleModel;
import com.example.CRM.repository.RoleRepository;
import com.example.CRM.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;


    @Override
    public RoleModel addNewRole(RoleModel roleModel) {
        Role role = new Role();
        role.setId(roleModel.getId());
        role.setCreateDate(roleModel.getCreateDate());
        role.setActive(true);
        role.setRoleName(roleModel.getRoleName());
        repository.save(role);
        return roleModel;
    }

    @Override
    public Role setInActiveRole(Role role, Long status) {
        role.setActive(true);
        return repository.save(role);
    }

    @Override
    public RoleModel getRoleById(Long id) {
        Role role = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Роль связанный с идентификатором " + id + " не найден"));
        return role.toModel();
    }

    @Override
    public List<RoleModel> getAllRole() {
        return repository
                .findAll()
                .stream()
                .map(Role::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public RoleModel deleteRoleById(Long id) {
        Role role = getById(id);
        Role deleteRole = setInActiveRole(role, -1L);
        return deleteRole.toModel();
    }


    public Role getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new
                        NotFoundException
                        ("Роль связанный с идентификатором " + id + " не найдено "));
    }
}
