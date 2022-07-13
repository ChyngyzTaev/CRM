package com.example.CRM.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersModel extends BaseModel{
    String userName;

    LocalDate birthDay;

    String email;

    String password;

    boolean isActive;
}
