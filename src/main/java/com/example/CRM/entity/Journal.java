package com.example.CRM.entity;

import com.example.CRM.model.JournalModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "journal")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Journal extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "general_types_id")
    GeneralRecord generalRecord;

    public JournalModel toModel(){
        return JournalModel.builder()
                .id(this.getId())
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .isActive(true)
                .build();
    }
}
