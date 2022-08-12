package com.example.CRM.controller;

import com.example.CRM.entity.User;
import com.example.CRM.entity.UserRole;
import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UpdateUserModel;
import com.example.CRM.service.ManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/add-new-manager")
    public ResponseEntity<?> addNewManager(@RequestBody CreateUserModel createUserModel, UserRole userRole){
        try {
            return new ResponseEntity<>(managerService.addNewManager(createUserModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-manager-by-id/{id}")
    public ResponseEntity<?> getManagerById (@PathVariable Long id){
        try {
            return new ResponseEntity<>(managerService.getManagerById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new  ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-manager-by-userName")
    public ResponseEntity<?> getManagerByUserName(@RequestBody User username){
        try {
            return new ResponseEntity<>(managerService.getManagerByUserName(username), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-manager-by-email")
    public ResponseEntity<?> getManagerByEmail(@RequestBody User email){
        try {
            return new ResponseEntity<>(managerService.getManagerByEmail(email), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new  ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-managers")
    public ResponseEntity<?> getAllManagers(){
        try {
            return new ResponseEntity<>(managerService.getAllManagers(), HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-manager")
    public ResponseEntity<?> updateManager(@RequestBody UpdateUserModel updateUserModel){
        try {
            return new ResponseEntity<>(managerService.updateManager(updateUserModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete-manager-by-userName")
    public ResponseEntity<?> deleteManagerByUserName(@RequestBody User username){
        try {
            return new ResponseEntity<>(managerService.deleteManagerByUserName(username), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete-user-by-id/{id}")
    public ResponseEntity<?> deleteManagerById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(managerService.deleteManagerById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
