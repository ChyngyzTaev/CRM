package com.example.CRM.service;

import com.example.CRM.model.GeneralRecordModel;

import java.util.List;

public interface GeneralRecordService {
    GeneralRecordModel addNewGeneralRecord(GeneralRecordModel generalRecordModel);

    GeneralRecordModel getGeneralRecordById(Long id);

    List<GeneralRecordModel> getAllGeneralRecord();

    GeneralRecordModel updateGeneralRecord(GeneralRecordModel generalRecordModel);

    GeneralRecordModel deleteGeneralRecordById(Long id);
}
