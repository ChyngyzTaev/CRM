package com.example.CRM.model.user;

import com.example.CRM.entity.User;
import com.example.CRM.enums.RolesEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserModel {
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

    Long isActive;

    RolesEnum rolesEnum;

    public User toUser(){
        return User.builder()
                .fullName(fullName)
                .username(username)
                .birthDay(birthday)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .isActive(isActive)
                .build();
    }
}
