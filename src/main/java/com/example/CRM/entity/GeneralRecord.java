package com.example.CRM.entity;

import com.example.CRM.model.GeneralRecordModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "general_record")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralRecord extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User users;

    @OneToOne
    @JoinColumn(name = "subscription_types_id", nullable = false, unique = true)
    SubscriptionTypes types;

    public GeneralRecordModel toModel(){
        return GeneralRecordModel.builder()
                .id(this.getId())
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .isActive(true)
                .build();
    }
}
