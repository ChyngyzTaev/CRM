package com.example.CRM.model.user;

import java.time.LocalDate;

public interface BaseModel {
    String getFullName();

    LocalDate getBirthDay();

    String getUsername();

    String getEmail();

    String getPassword();
}
