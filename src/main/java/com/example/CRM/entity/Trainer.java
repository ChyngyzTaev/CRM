package com.example.CRM.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trainer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Trainer extends BaseEntity{
    @Column(name = "full_name")
    String fullName;

    @Column(name = "age")
    Long age;

    boolean isActive;

    @ManyToMany
    @JoinColumn(name = "chart_id")
    List<Chart> charts;
}
