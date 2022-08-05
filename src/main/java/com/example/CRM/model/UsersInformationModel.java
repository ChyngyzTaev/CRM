package com.example.CRM.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersInformationModel extends BaseModel{

    // " " "" null
    @NotBlank
    String fullName;

    @NotBlank
    LocalDate birthday;

    @NotBlank
    String phoneNumber;
}
