package com.example.CRM.service;

import com.example.CRM.entity.GeneralRecord;
import com.example.CRM.model.GeneralRecordModel;

import java.util.List;

public interface GeneralRecordService extends BaseService<GeneralRecord>{
    GeneralRecordModel addNewGeneralRecord(GeneralRecordModel generalRecordModel);

    GeneralRecord getGeneralRecordById(Long id);

    List<GeneralRecord> getAllGeneralRecord();

    GeneralRecordModel updateGeneralRecord(GeneralRecordModel generalRecordModel);

    GeneralRecordModel deleteGeneralRecordById(Long id);
}
