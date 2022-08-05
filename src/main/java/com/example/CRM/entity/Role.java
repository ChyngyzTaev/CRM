package com.example.CRM.entity;

import com.example.CRM.model.RoleModel;
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
    @Column(name = "role_name")
    String roleName;

    public RoleModel toModel(){
        return RoleModel.builder()
                .id(this.getId())
                .roleName(roleName)
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .isActive(true)
                .build();
    }
}
