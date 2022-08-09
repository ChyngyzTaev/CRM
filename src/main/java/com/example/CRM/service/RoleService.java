package com.example.CRM.service;

import com.example.CRM.entity.Role;
import com.example.CRM.model.Role.CreateRoleModel;
import com.example.CRM.model.Role.RoleModel;
import com.example.CRM.model.Role.UpdateRoleModel;

import java.util.List;

public interface RoleService{
    CreateRoleModel addNewRole(CreateRoleModel roleModel);

    Role setInActiveRole(Role role, Long status);

    RoleModel getRoleById(Long id);

    Role getRoleByRoleName(String roleName);

    List<RoleModel> getAllRole();

    UpdateRoleModel updateRole(UpdateRoleModel roleModel);

    RoleModel deleteRoleById(Long id);
}
