package com.example.CRM.entity;

import com.example.CRM.model.user.UserModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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

    @Column(name = "birth_day" )
    LocalDate birthDay;

    @Column(name = "email", length = 100, unique = true)
    String email;

    @Column(name = "password", length = 100, nullable = false)
    String password;

    @Column(name = "phone_number")
    String phoneNumber;

    @OneToOne
    @JoinColumn(name = "subscription_types_id")
    SubscriptionTypes subscriptionTypes;

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
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
