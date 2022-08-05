package com.example.CRM.service;

import com.example.CRM.model.JournalModel;

import java.util.List;

public interface JournalService {
    JournalModel addNewJournal(JournalModel journalModel);

    JournalModel getJournalById(Long id);

    List<JournalModel> getAllJournal();

    void deleteJournalById(Long id);
}
