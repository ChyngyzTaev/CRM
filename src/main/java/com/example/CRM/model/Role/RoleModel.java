package com.example.CRM.model.Role;

import com.example.CRM.enums.RolesEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleModel{
    Long id;

    Long isActive;

    LocalDateTime createDate;

    LocalDateTime updateDate;

    RolesEnum rolesEnum;
}
