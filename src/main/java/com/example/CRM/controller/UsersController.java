package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.UsersModel;
import com.example.CRM.request.AuthenticationRequest;
import com.example.CRM.response.AuthenticationResponse;
import com.example.CRM.security.jwt.JwtUtil;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
       return userService.login(authenticationRequest);
    }

    @PostMapping("/add-new-user")
    public ResponseEntity<?> addNewUser(@RequestBody UsersModel usersModel){
        try {
            return new ResponseEntity<>(userService.addNewUser(usersModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-user-by/{id}")
    public ResponseEntity<?> getUserById (@PathVariable ("id") Long id){
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new  ResponseEntity<>(notFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers(){
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-user-by/{id}")
    public void deleteUserById(@PathVariable ("id") Long id){
        userService.deleteUserById(id);
    }
}
