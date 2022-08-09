package com.example.CRM.model.Role;

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
public class UpdateRoleModel {
    Long id;

    boolean isActive;

    LocalDateTime createDate;

    LocalDateTime updateDate;

    @NotBlank
    RolesEnum roleName;
}
