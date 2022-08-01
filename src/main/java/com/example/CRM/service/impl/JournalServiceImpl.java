package com.example.CRM.service.impl;

import com.example.CRM.convert.BaseConvert;
import com.example.CRM.entity.Journal;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.JournalModel;
import com.example.CRM.repository.JournalRepository;
import com.example.CRM.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository repository;

    @Autowired
    private BaseConvert<JournalModel, Journal> convert;


    @Override
    public JournalModel addNewJournal(JournalModel journalModel) {
        Journal journal = new Journal();
        journal.setId(journalModel.getId());
        journal.setCreateDate(journalModel.getCreateDate());
        repository.save(journal);
        return journalModel;
    }

    @Override
    public Journal setInActiveJournal(Journal journal, Long status) {
        journal.setActive(true);
        return repository.save(journal);
    }

    @Override
    public JournalModel getJournalById(Long id) {
        return convert.convertFromEntity(getById(id));
    }

    @Override
    public List<JournalModel> getAllJournal() {
        return getAll()
                .stream()
                .map(convert::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteJournalById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Journal save(Journal journal) {
        return repository.save(journal);
    }

    @Override
    public Journal getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Журнал связанный с идентификатором " + id + "не найдено"));
    }

    @Override
    public List<Journal> getAll() {
        return repository.findAll();
    }
}
