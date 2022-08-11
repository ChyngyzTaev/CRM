package com.example.CRM.model.user;

import com.example.CRM.enums.RolesEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserModel {
    Long id;

    Long isActive;

    LocalDateTime createDate;

    LocalDateTime updateDate;

    @NotBlank
    String fullName;

    @NotBlank
    String username;


    @NotBlank
    LocalDate birthday;

    @NotBlank
    String phoneNumber;

    @NotBlank
    String email;

    @NotBlank
    String password;

    RolesEnum rolesEnum;
}
