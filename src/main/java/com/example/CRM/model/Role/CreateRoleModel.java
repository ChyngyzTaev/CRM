package com.example.CRM.model.Role;

import com.example.CRM.entity.UserRole;
import com.example.CRM.enums.RolesEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateRoleModel {
    Long isActive;

    LocalDateTime createDate;

    LocalDateTime updateDate;

    @NotBlank
    RolesEnum roleName;

    public UserRole toRole(){
        return UserRole.builder()
                .id(null)
                .isActive(isActive)
                .roleName(roleName)
                .build();
    }
}
