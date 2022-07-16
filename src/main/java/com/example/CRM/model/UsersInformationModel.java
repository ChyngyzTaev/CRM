package com.example.CRM.model;

import com.example.CRM.entity.Users;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersInformationModel extends BaseModel{

    // " " "" null
    @NotBlank
    String fullName;

    LocalDate birthday;

    String phoneNumber;

    Users users;
}
