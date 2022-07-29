package com.example.CRM.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserModel extends BaseModel{
    String email;

    String password;

    boolean isActive;

    List<RoleModel> roles;
}
