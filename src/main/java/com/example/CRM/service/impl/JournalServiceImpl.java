package com.example.CRM.service.impl;

import com.example.CRM.entity.Journal;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.JournalModel;
import com.example.CRM.repository.JournalRepository;
import com.example.CRM.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository repository;

    @Override
    public JournalModel addNewJournal(JournalModel journalModel) {
        Journal journal = new Journal();
        journal.setGeneralRecord(journalModel.getGeneralRecord());
        repository.save(journal);
        return journalModel;
    }

    @Override
    public Journal getJournalById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new
                        NotFoundException("Журнал связонный с id " + id + " не найден"));
    }

    @Override
    public List<Journal> getAllJournal() {
        return repository.findAll();
    }

    @Override
    public void deleteJournalById(Long id) {
        repository.deleteById(id);
    }
}
