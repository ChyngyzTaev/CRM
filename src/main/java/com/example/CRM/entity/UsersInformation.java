package com.example.CRM.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users_information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersInformation extends BaseEntity{
    @Column(name = "full_name")
    String fullName;

    @Column(name = "birth_day")
    LocalDate birthDay;

    @Column(name = "phone_number")
    String phoneNumber;

    boolean isActive;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User users;
}
