package com.example.CRM.service;

import com.example.CRM.entity.GeneralRecord;
import com.example.CRM.entity.Journal;
import com.example.CRM.model.GeneralRecordModel;

import java.util.List;

public interface GeneralRecordService extends BaseService<GeneralRecord>{
    GeneralRecordModel addNewGeneralRecord(GeneralRecordModel generalRecordModel);

    GeneralRecordModel getGeneralRecordById(Long id);

    List<GeneralRecordModel> getAllGeneralRecord();
}
