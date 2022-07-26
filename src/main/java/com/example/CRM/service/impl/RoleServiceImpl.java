package com.example.CRM.service.impl;

import com.example.CRM.entity.Role;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.RoleModel;
import com.example.CRM.repository.RoleRepository;
import com.example.CRM.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public RoleModel addNewRole(RoleModel roleModel) {
        Role role = new Role();
        role.setId(roleModel.getId());
        role.setCreateDate(roleModel.getCreateDate());
        role.setRoleName(roleModel.getRoleName());
        repository.save(role);
        return roleModel;
    }

    @Override
    public Role getRoleById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new
                        NotFoundException("Роль пользователя связонный с id " + id + " не найдено"));
    }

    @Override
    public List<Role> getAllRole() {
        return repository.findAll();
    }

    @Override
    public void deleteRole(Long id) {
        Role role = new Role();
        role.setActive(false);
        repository.save(role);
    }
}
