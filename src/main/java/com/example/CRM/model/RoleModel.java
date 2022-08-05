package com.example.CRM.model;

import com.example.CRM.entity.Role;
import com.example.CRM.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleModel extends BaseModel{

    @NotBlank
    String roleName;

    User user;

    public Role toRoleEntity() {
        return Role.builder()
                .id(null)
                .roleName(roleName)
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .isActive(true)
                .build();
    }
}
