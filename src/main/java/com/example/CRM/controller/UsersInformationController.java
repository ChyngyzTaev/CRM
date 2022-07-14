package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.UsersInformationModel;
import com.example.CRM.service.UsersInformationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/information")
public class UsersInformationController {
    @Autowired
    private UsersInformationService informationService;

    @PostMapping("/add-user-info")
    public ResponseEntity<?> addUserInfo(@RequestBody UsersInformationModel informationModel) {
        try {
            return new ResponseEntity<>(informationService.addUserInfo(informationModel), HttpStatus.OK);
        } catch (BadRequestException badRequestException) {
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-info-by{id}")
    public ResponseEntity<?> getUserInfoById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(informationService.getUserInfoById(id), HttpStatus.OK);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-user-info")
    public ResponseEntity<?> getAllUserInfo(UsersInformationModel informationModel) {
        try {
            return new ResponseEntity<>(informationService.getAllUserInfo(informationModel), HttpStatus.OK);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-user-info")
    public ResponseEntity<?> updateUserInfo(UsersInformationModel informationModel){
        try {
            return new ResponseEntity<>(informationService.updateUserInfo(informationModel), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/delete-user-info-by{id}")
    public void deleteUserInoById(Long id){
        informationService.deleteUserInfoById(id);
    }
}
