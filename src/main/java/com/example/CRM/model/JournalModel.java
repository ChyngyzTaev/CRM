package com.example.CRM.model;

import com.example.CRM.entity.GeneralRecord;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JournalModel extends BaseModel{
    GeneralRecordModel generalRecordModel;
}
