package com.example.CRM.service;

import com.example.CRM.entity.UserRole;
import com.example.CRM.model.Role.CreateRoleModel;
import com.example.CRM.model.Role.RoleModel;
import com.example.CRM.model.Role.UpdateRoleModel;

import java.util.List;

public interface RoleService{
    RoleModel addNewRole(CreateRoleModel createRoleModel);

    UserRole setInActiveRole(UserRole userRole, Long status);

    RoleModel getRoleById(Long id);

    UserRole getRoleByRoleName(String roleName);

    List<RoleModel> getAllRole();

    boolean updateRole(UpdateRoleModel roleModel);

    RoleModel deleteRoleById(Long id);
}
