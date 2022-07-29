package com.example.CRM.service.impl;

import com.example.CRM.convert.BaseConvert;
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

    @Autowired
    private BaseConvert<RoleModel, Role> convert;


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
    public Role setInActiveUser(Role role, Long status) {
        role.setActive(true);
        return repository.save(role);
    }

    @Override
    public RoleModel getRoleById(Long id) {
        return convert.convertFromEntity(getById(id));
    }

    @Override
    public List<RoleModel> getAllRole() {
        return getAll()
                .stream()
                .map(convert::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public RoleModel deleteRole(Long id) {
        Role role = getById(id);
        Role deleteRole = setInActiveUser(role, -1L);
        return convert.convertFromEntity(deleteRole);
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public Role getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new
                        NotFoundException("Роль связанный с идентификатором " + id + " не найдено "));
    }

    @Override
    public List<Role> getAll() {
        return repository.findAll();
    }
}
