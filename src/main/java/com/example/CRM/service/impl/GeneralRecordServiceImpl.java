package com.example.CRM.service.impl;

import com.example.CRM.entity.GeneralRecord;
import com.example.CRM.exception.NotFoundException;
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
    public GeneralRecordModel addNewGeneralRecord(GeneralRecordModel generalRecordModel) {
        GeneralRecord generalRecord = new GeneralRecord();
        generalRecord.setUsers(generalRecordModel.getUsers());
        generalRecord.setTypes(generalRecordModel.getTypes());
        repository.save(generalRecord);
        return generalRecordModel;
    }

    @Override
    public GeneralRecord getGeneralRecordById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Запись не надена"));
    }

    @Override
    public List<GeneralRecord> getAllGeneralRecord() {
        return repository.findAll();
    }

    @Override
    public GeneralRecordModel updateGeneralRecord(GeneralRecordModel generalRecordModel) {
        return null;
    }

    @Override
    public GeneralRecordModel deleteGeneralRecordById(Long id) {
        return null;
    }
}
