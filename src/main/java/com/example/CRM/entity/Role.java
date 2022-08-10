package com.example.CRM.entity;

import com.example.CRM.enums.RolesEnum;
import com.example.CRM.model.Role.RoleModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends BaseEntity{
    @Column(name = "role_name", nullable = false)
    String roleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_enum")
    RolesEnum rolesEnum;

    public RoleModel toModel(){
        return RoleModel.builder()
                .id(this.getId())
                .rolesEnum(rolesEnum)
                .rolesEnum(rolesEnum)
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .build();
    }
}
