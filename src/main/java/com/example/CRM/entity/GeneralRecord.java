package com.example.CRM.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "general_record")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralRecord extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "chart_id")
    Chart chart;

    @OneToOne
    @JoinColumn(name = "list_exercises_id")
    ListExercises listExercises;

    @OneToOne
    @JoinColumn(name = "role_id")
    UserRole userRole;

    @OneToOne
    @JoinColumn(name = "subscription_types_id")
    SubscriptionTypes subscriptionTypes;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}
