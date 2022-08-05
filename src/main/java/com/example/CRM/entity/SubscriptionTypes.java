package com.example.CRM.entity;

import com.example.CRM.model.SubscriptionTypesModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subscription_types")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionTypes extends BaseEntity {
    @Column(name = "number_of_month")
    Long numberOfMonth;

    @Column(name = "number_of_week")
    Long numberOfWeek;

    @Column(name = "number_of_day")
    Long numberOfDay;

    public SubscriptionTypesModel toModel(){
        return SubscriptionTypesModel.builder()
                .id(this.getId())
                .numberOfMonth(numberOfMonth)
                .numberOfWeek(numberOfWeek)
                .numberOfDay(numberOfDay)
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .isActive(true)
                .build();
    }
}
