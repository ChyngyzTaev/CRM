package com.example.CRM.service;

import com.example.CRM.entity.Role;
import com.example.CRM.entity.Schedule;
import com.example.CRM.model.RoleModel;

import java.util.List;

public interface RoleService extends BaseService<Role>{
    RoleModel addNewRole(RoleModel roleModel);

    Role setInActiveUser(Role role, Long status);

    RoleModel getRoleById(Long id);

    List<RoleModel> getAllRole();

    RoleModel deleteRole(Long id);
}
