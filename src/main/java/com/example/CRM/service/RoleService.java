package com.example.CRM.service;

import com.example.CRM.entity.Role;
import com.example.CRM.model.RoleModel;

import java.util.List;

public interface RoleService {
    RoleModel addNewRole(RoleModel roleMode);

    Role getRoleById(Long id);

    List<Role> getAllRole();

    void deleteRole(Long id);
}
