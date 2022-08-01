package com.example.CRM.service.impl;

import com.example.CRM.convert.BaseConvert;
import com.example.CRM.entity.GeneralRecord;
import com.example.CRM.entity.Role;
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

    @Autowired
    private BaseConvert<GeneralRecordModel, GeneralRecord> convert;


    @Override
    public GeneralRecordModel addNewGeneralRecord(GeneralRecordModel generalRecordModel) {
        GeneralRecord generalRecord = new GeneralRecord();
        generalRecord.setId(generalRecordModel.getId());
        generalRecord.prePersist();
        generalRecord.prePersist();
        repository.save(generalRecord);
        return generalRecordModel;
    }


    @Override
    public GeneralRecordModel getGeneralRecordById(Long id) {
        return convert.convertFromEntity(getById(id));
    }

    @Override
    public List<GeneralRecordModel> getAllGeneralRecord() {
        return getAll()
                .stream()
                .map(convert::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public GeneralRecord save(GeneralRecord generalRecord) {
        return repository.save(generalRecord);
    }

    @Override
    public GeneralRecord getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Запись связанный с идентификатором " + id + "не найдено"));
    }

    @Override
    public List<GeneralRecord> getAll() {
        return repository.findAll();
    }
}
