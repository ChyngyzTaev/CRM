package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.UsersInformationModel;
import com.example.CRM.service.UserInformationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/-users-information")
public class UsersInformationController {
    @Autowired
    private UserInformationService service;

    @PostMapping("/add-new-user-info")
    public ResponseEntity<?> addUserInfo(@RequestBody UsersInformationModel informationModel) {
        try {
            return new ResponseEntity<>(service.addUserInfo(informationModel), HttpStatus.OK);
        } catch (BadRequestException badRequestException) {
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-info/{id}")
    public ResponseEntity<?> getUserInfoById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getUserInfoById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-user-info")
    public ResponseEntity<?> getAllUserInfo() {
        try {
            return new ResponseEntity<>(service.getAllUserInfo(), HttpStatus.OK);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-user-info")
    public ResponseEntity<?> updateUserInfo(@Valid @RequestBody UsersInformationModel informationModel){
        try {
            return new ResponseEntity<>(service.updateUserInfo(informationModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-user-info-by-id/{id}")
    public ResponseEntity<?> deleteUserInoById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.deleteUserInfoById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
