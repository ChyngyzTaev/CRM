package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.ListExercisesModel;
import com.example.CRM.service.ListExercisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-schedule")
public class ListExercisesController {
    @Autowired
    private ListExercisesService service;

    @PostMapping("/add-new-schedule")
    public ResponseEntity<?> addNewSchedule(@RequestBody ListExercisesModel scheduleModel){
        try {
            return new ResponseEntity<>(service.addNewSchedule(scheduleModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-schedule-by-id/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getScheduleById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-schedule")
    public ResponseEntity<?> getAllSchedule(){
        try {
            return new ResponseEntity<>(service.getAllSchedule(), HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-schedule")
    public ResponseEntity<?> updateSchedule(@RequestBody ListExercisesModel scheduleModel){
        try {
            return new ResponseEntity<>(service.updateSchedule(scheduleModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-schedule-by-id/{id}")
    public ResponseEntity<?> deleteScheduleById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.deleteScheduleById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
