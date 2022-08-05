package com.example.CRM.entity;

import com.example.CRM.model.UserModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
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

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @Email
    String email;

    @Column(name = "password", length = 100, nullable = false)
    String password;

    @ManyToMany
    @JoinColumn(name = "role_id", nullable = false)
    List<Role> role;

    @OneToOne(mappedBy = "user")
    private UsersInformation usersInformation;

    public UserModel toModel(){
        return UserModel.builder()
                .id(this.getId())
                .email(email)
                .password(password)
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .isActive(true)
                .build();
    }
}
