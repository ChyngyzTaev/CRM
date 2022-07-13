package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.UsersModel;
import com.example.CRM.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UsersController {
    @Autowired
    private UserService userService;

    @PostMapping("/add-new-user")
    public ResponseEntity<?> addNewUser(@RequestBody UsersModel usersModel){
        try {
            return new ResponseEntity<>(userService.addNewUser(usersModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getUserById (@PathVariable Long id){
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new  ResponseEntity<>(notFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers(){
        try {
            return new ResponseEntity<>( userService.getAllUsers(), HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUserById(Long id){
        userService.deleteUserById(id);
    }
}
