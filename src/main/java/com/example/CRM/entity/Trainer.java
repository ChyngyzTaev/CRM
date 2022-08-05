package com.example.CRM.entity;

import com.example.CRM.model.TrainerModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trainer")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Trainer extends BaseEntity{
    @Column(name = "full_name")
    String fullName;

    @Column(name = "age")
    Long age;

    @ManyToMany
    @JoinColumn(name = "chart_id")
    List<Chart> charts;

    public TrainerModel toModel(){
        return TrainerModel.builder()
                .id(this.getId())
                .fullName(fullName)
                .age(age)
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .isActive(true)
                .build();
    }
}
