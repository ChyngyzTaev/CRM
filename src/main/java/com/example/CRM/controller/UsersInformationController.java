package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.UserInformationModel;
import com.example.CRM.service.UsersInformationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/information")
public class UsersInformationController {
    @Autowired
    private UsersInformationService service;

    @PostMapping("/add-new-user-info")
    public ResponseEntity<?> addUserInfo(@RequestBody UserInformationModel informationModel) {
        try {
            return new ResponseEntity<>(service.addUserInfo(informationModel), HttpStatus.OK);
        } catch (BadRequestException badRequestException) {
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-info/{id}")
    public ResponseEntity<?> getUserInfoById(@PathVariable ("id") Long id) {
        try {
            return new ResponseEntity<>(service.getUserInfoById(id), HttpStatus.OK);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-user-info")
    public ResponseEntity<?> getAllUserInfo(UserInformationModel informationModel) {
        try {
            return new ResponseEntity<>(service.getAllUserInfo(informationModel), HttpStatus.OK);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-user-info")
    public ResponseEntity<?> updateUserInfo(@Valid @RequestBody UserInformationModel informationModel){
        try {
            return new ResponseEntity<>(service.updateUserInfo(informationModel), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/delete-user-info/{id}")
    public void deleteUserInoById(@PathVariable("id") Long id){
        service.deleteUserInfoById(id);
    }
}
