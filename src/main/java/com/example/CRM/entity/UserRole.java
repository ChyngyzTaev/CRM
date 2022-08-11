package com.example.CRM.entity;

import com.example.CRM.enums.RolesEnum;
import com.example.CRM.model.Role.RoleModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRole extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    RolesEnum roleName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    public RoleModel toModel(){
        return RoleModel.builder()
                .id(this.getId())
                .roleName(roleName)
                .build();
    }
}
