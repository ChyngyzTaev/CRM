package com.example.CRM.service;

import com.example.CRM.entity.Role;
import com.example.CRM.model.RoleModel;

import java.util.List;

public interface RoleService{
    RoleModel addNewRole(RoleModel roleModel);

    Role setInActiveRole(Role role, Long status);

    RoleModel getRoleById(Long id);

    List<RoleModel> getAllRole();

    RoleModel deleteRoleById(Long id);
}
