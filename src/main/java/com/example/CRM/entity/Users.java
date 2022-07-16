package com.example.CRM.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

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

    @ManyToMany
    @JoinColumn(name = "role_id", nullable = false)
    private List<Role> role;
}
