package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.ListExercises.CreateListExercisesModel;
import com.example.CRM.model.ListExercises.UpdateListExercisesModel;
import com.example.CRM.service.ListExercisesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/list-exercises")
public class ListExercisesController {
    @Autowired
    private ListExercisesService listExercisesService;

    @PostMapping("/add-new-list-exercises")
    public ResponseEntity<?> addNewSchedule(@RequestBody CreateListExercisesModel scheduleModel){
        try {
            return new ResponseEntity<>(listExercisesService.addNewSchedule(scheduleModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-list-exercises-by-id/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(listExercisesService.getScheduleById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-list-exercises")
    public ResponseEntity<?> getAllSchedule(){
        try {
            return new ResponseEntity<>(listExercisesService.getAllSchedule(), HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-list-exercises")
    public ResponseEntity<?> updateSchedule(@RequestBody UpdateListExercisesModel scheduleModel){
        try {
            return new ResponseEntity<>(listExercisesService.updateSchedule(scheduleModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-list-exercises-by-id/{id}")
    public ResponseEntity<?> deleteScheduleById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(listExercisesService.deleteScheduleById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
