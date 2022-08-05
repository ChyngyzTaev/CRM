package com.example.CRM.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "create_date")
    LocalDateTime createDate;

    @Column(name = "is_active")
    boolean isActive;

    @Column(name = "update_date")
    LocalDateTime updateDate;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }
}
