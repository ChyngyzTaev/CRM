package com.example.CRM.model;

import com.example.CRM.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleModel extends BaseModel{
    String roleName;

    boolean isActive;

    User user;
}
