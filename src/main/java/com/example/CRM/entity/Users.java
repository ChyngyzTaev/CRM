package com.example.CRM.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users extends BaseEntity{

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @Email
    String email;

    @Column(name = "password", length = 100, nullable = false)
    String password;

    @Column(name = "is_active")
    boolean isActive;
}
