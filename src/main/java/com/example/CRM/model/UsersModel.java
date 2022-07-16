package com.example.CRM.model;

import com.example.CRM.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersModel extends BaseModel{
    String userName;

    LocalDate birthDay;

    String email;

    String password;

    List<RoleModel> roles;
}
