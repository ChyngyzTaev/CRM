package com.example.CRM.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "general_record")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralRecord extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    Users users;

    @OneToOne
    @JoinColumn(name = "subscription_types_id", nullable = false, unique = true)
    SubscriptionTypes types;

    boolean isTrainer;

    @OneToOne
    @JoinColumn(name = "trainer_id")
    Users trainer;
}
