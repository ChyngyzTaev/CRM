package com.example.CRM.service;

import com.example.CRM.entity.Journal;
import com.example.CRM.model.JournalModel;

import java.util.List;

public interface JournalService extends BaseService<Journal> {
    JournalModel addNewJournal(JournalModel journalModel);

    JournalModel getJournalById(Long id);

    List<JournalModel> getAllJournal();

    void deleteJournalById(Long id);
}
