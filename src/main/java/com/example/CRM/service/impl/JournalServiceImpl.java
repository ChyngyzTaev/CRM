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
    public Journal save(Journal journal) {
        return null;
    }

    @Override
    public Journal getById(Long id) {
        return null;
    }

    @Override
    public List<Journal> getAll() {
        return null;
    }

    @Override
    public JournalModel addNewJournal(JournalModel journalModel) {
        return null;
    }

    @Override
    public Journal getJournalById(Long id) {
        return null;
    }

    @Override
    public List<Journal> getAllJournal() {
        return null;
    }

    @Override
    public void deleteJournalById(Long id) {

    }
}
