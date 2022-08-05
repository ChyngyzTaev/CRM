package com.example.CRM.entity;

import com.example.CRM.model.UsersInformationModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users_information")
@Getter
@Setter
@SuperBuilder
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

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User user;

    public UsersInformationModel toModel(){
        return UsersInformationModel.builder()
                .id(this.getId())
                .fullName(fullName)
                .birthday(birthDay)
                .phoneNumber(phoneNumber)
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .isActive(true)
                .build();
    }
}
