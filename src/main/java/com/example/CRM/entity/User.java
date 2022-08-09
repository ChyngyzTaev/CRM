package com.example.CRM.entity;

import com.example.CRM.model.user.UserModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity{
    @Column(name = "full_name", nullable = false)
    String fullName;

    @Column(name = "username", nullable = false, unique = true)
    String username;

    @Column(name = "age", nullable = false)
    Long age;

    @Column(name = "birth_day", nullable = false)
    LocalDate birthDay;

    @Column(name = "phone_number", nullable = false)
    String phoneNumber;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @Email
    String email;

    @Column(name = "password", length = 100, nullable = false)
    String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @ManyToMany
    @JoinColumn(name = "chart_id")
    List<Chart> charts;

    @ManyToMany
    @JoinColumn(name = "exercises_id")
    List<ListExercises> exercises;

    public UserModel toModel(){
        return UserModel.builder()
                .id(this.getId())
                .fullName(fullName)
                .username(username)
                .birthday(birthDay)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .age(age)
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .isActive(true)
                .build();
    }
}
