package com.example.CRM.service.impl;

import com.example.CRM.entity.GeneralRecord;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.GeneralRecordModel;
import com.example.CRM.repository.GeneralRecordRepository;
import com.example.CRM.service.GeneralRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralRecordServiceImpl implements GeneralRecordService {
    @Autowired
    private GeneralRecordRepository repository;


    @Override
    public GeneralRecordModel addNewGeneralRecord(GeneralRecordModel generalRecordModel) {
        GeneralRecord generalRecord = new GeneralRecord();
        generalRecord.setId(generalRecordModel.getId());
        generalRecord.prePersist();
        repository.save(generalRecord);
        return generalRecordModel;
    }


    @Override
    public GeneralRecordModel getGeneralRecordById(Long id) {
        GeneralRecord generalRecord = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Общая запись связанная с идентификатором " + id + " не найдена"));
        return generalRecord.toModel();
    }

    @Override
    public List<GeneralRecordModel> getAllGeneralRecord() {
        return repository.findAll()
                .stream()
                .map(GeneralRecord::toModel)
                .collect(Collectors.toList());
    }
}
