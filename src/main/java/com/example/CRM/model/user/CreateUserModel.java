package com.example.CRM.model.user;

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
    Long age;

    @NotBlank
    LocalDate birthday;

    @NotBlank
    String phoneNumber;

    @NotBlank
    String email;

    @NotBlank
    String password;

    Long isActive;
}
