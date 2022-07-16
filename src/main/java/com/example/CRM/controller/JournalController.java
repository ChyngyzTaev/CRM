package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.JournalModel;
import com.example.CRM.service.JournalService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-journal")
public class JournalController {
    @Autowired
    private JournalService service;

    @PostMapping("/add-new-journal")
    public ResponseEntity<?> addNewJournal(@RequestBody JournalModel journalModel){
        try {
            return new ResponseEntity<>(service.addNewJournal(journalModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-journal/{id}")
    public ResponseEntity<?> getJournalById(@PathVariable ("id") Long id){
        try {
            return new ResponseEntity<>(service.getJournalById(id), HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-journal")
    public ResponseEntity<?> getAllHJournal(){
        try {
            return new ResponseEntity<>(service.getAllJournal(), HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete-journal/{id}")
    public void deleteJournalById(@PathVariable ("id") Long id){
        service.deleteJournalById(id);
    }
}
