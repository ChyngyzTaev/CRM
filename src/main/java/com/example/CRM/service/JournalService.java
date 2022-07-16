package com.example.CRM.service;

import com.example.CRM.entity.Journal;
import com.example.CRM.model.JournalModel;

import java.util.List;

public interface JournalService {
    JournalModel addNewJournal(JournalModel journalModel);

    Journal getJournalById(Long id);

    List<Journal> getAllJournal();

    void deleteJournalById(Long id);
}
