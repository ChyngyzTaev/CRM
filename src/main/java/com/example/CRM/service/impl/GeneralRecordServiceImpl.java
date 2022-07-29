package com.example.CRM.service.impl;

import com.example.CRM.entity.GeneralRecord;
import com.example.CRM.model.GeneralRecordModel;
import com.example.CRM.repository.GeneralRecordRepository;
import com.example.CRM.service.GeneralRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralRecordServiceImpl implements GeneralRecordService {
    @Autowired
    private GeneralRecordRepository repository;


    @Override
    public GeneralRecord save(GeneralRecord generalRecord) {
        return null;
    }

    @Override
    public GeneralRecord getById(Long id) {
        return null;
    }

    @Override
    public List<GeneralRecord> getAll() {
        return null;
    }

    @Override
    public GeneralRecordModel addNewGeneralRecord(GeneralRecordModel generalRecordModel) {
        return null;
    }

    @Override
    public GeneralRecordModel getGeneralRecordById(Long id) {
        return null;
    }

    @Override
    public List<GeneralRecordModel> getAllGeneralRecord() {
        return null;
    }

    @Override
    public GeneralRecordModel deleteGeneralRecordById(Long id) {
        return null;
    }
}
