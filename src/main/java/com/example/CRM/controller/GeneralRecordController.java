package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.GeneralRecordModel;
import com.example.CRM.service.GeneralRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/general-record")
public class GeneralRecordController {
    @Autowired
    private GeneralRecordService service;

    @PostMapping("/add-new-general-record")
    public ResponseEntity<?> addNewGeneralRecord(@RequestBody GeneralRecordModel generalRecordModel) {
        try {
            return new ResponseEntity<>(service.addNewGeneralRecord(generalRecordModel), HttpStatus.OK);
        } catch (BadRequestException badRequestException) {
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-general-record-by-id/{id}")
    public ResponseEntity<?> getGeneralRecordById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getGeneralRecordById(id), HttpStatus.OK);
        } catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-general-record")
    public ResponseEntity<?> getAllGeneralRecord(){
        try {
            return new ResponseEntity<>(service.getAllGeneralRecord(),HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
