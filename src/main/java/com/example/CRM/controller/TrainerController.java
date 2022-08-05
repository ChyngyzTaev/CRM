package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.SubscriptionTypesModel;
import com.example.CRM.model.TrainerModel;
import com.example.CRM.service.TrainerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api-trainer")
public class TrainerController {
    @Autowired
    private TrainerService service;

    @PostMapping("/add-new-trainer")
    public ResponseEntity<?> addNewTrainer(@RequestBody TrainerModel trainerModel){
        try {
            return new ResponseEntity<>(service.addNewTrainer(trainerModel) , HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-trainer-by-id/{id}")
    public ResponseEntity<?> getTrainerById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getTrainerById(id),HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-trainer")
    public ResponseEntity<?> getAllTrainer(){
        try {
            return new ResponseEntity<>(service.getAllTrainer(), HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-trainer-by-id/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.deleteTrainerById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
