package com.example.CRM.service.impl;

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


    @Override
    public JournalModel addNewJournal(JournalModel journalModel) {
        Journal journal = new Journal();
        journal.setId(journalModel.getId());
        journal.setCreateDate(journalModel.getCreateDate());
        repository.save(journal);
        return journalModel;
    }

    @Override
    public JournalModel getJournalById(Long id) {
        Journal journal = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Журнал связанный с идентификатором " + id + " не найден"));
        return journal.toModel();
    }

    @Override
    public List<JournalModel> getAllJournal() {
        return repository
                .findAll()
                .stream()
                .map(Journal::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteJournalById(Long id) {
        repository.deleteById(id);
    }
}
