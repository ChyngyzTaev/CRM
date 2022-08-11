package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.user.AuthModel;
import com.example.CRM.request.AuthenticationRequest;
import com.example.CRM.response.AuthenticationResponse;
import com.example.CRM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthModel authModel) {
        try {
            return new ResponseEntity<>(userService.login(authModel), HttpStatus.OK);
        }catch (NotFoundException  e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка сервера", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private ResponseEntity<?> getErrorAuthorizationMessage(String message) {
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}


