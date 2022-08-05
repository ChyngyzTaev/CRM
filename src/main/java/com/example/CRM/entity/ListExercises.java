package com.example.CRM.entity;

import com.example.CRM.enums.WeekDayEnum;
import com.example.CRM.model.ListExercisesModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "list_exercises")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//Cписок упражений
public class ListExercises extends BaseEntity{

    @Column(name = "name_exercise")
    String nameExercise;

    @OneToMany
    @JoinColumn(name = "user_id")
    List<User> user;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekday_enums")
    WeekDayEnum weekDayEnum;



    public ListExercisesModel toModel(){
        return ListExercisesModel.builder()
                .id(this.getId())
                .nameExercise(nameExercise)
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .isActive(true)
                .build();
    }
}
