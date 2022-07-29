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
    public Role save(Role role) {
        return null;
    }

    @Override
    public Role getById(Long id) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public RoleModel addNewRole(RoleModel roleMode) {
        return null;
    }

    @Override
    public Role getRoleById(Long id) {
        return null;
    }

    @Override
    public List<Role> getAllRole() {
        return null;
    }

    @Override
    public void deleteRole(Long id) {

    }
}
