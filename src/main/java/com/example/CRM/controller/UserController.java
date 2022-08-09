package com.example.CRM.controller;

import com.example.CRM.entity.Role;
import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UserModel;
import com.example.CRM.request.AuthenticationRequest;
import com.example.CRM.response.AuthenticationResponse;
import com.example.CRM.security.jwt.JwtUtil;
import com.example.CRM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/add-new-user")
    public ResponseEntity<?> addNewUser(@RequestBody CreateUserModel createUserModel){
        try {
            return new ResponseEntity<>(service.addNewUser(createUserModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-new-trainer")
    public ResponseEntity<?> addNewTrainer(@RequestBody CreateUserModel createUserModel ){
        try {
            return new ResponseEntity<>(service.addNewTrainer(createUserModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-new-manager")
    public ResponseEntity<?> addNewManager(@RequestBody CreateUserModel createUserModel){
        try {
            return new ResponseEntity<>(service.addNewManager(createUserModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-new-admin")
    public ResponseEntity<?> addNewAdmin(@RequestBody CreateUserModel createUserModel ){
        try {
            return new ResponseEntity<>(service.addNewAdmin(createUserModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<?> getUserById (@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new  ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-trainer-by-id/{id}")
    public ResponseEntity<?> getTrainerById (@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getTrainerById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new  ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-manager-by-id/{id}")
    public ResponseEntity<?> getManagerById (@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getManagerById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new  ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-admin-by-id/{id}")
    public ResponseEntity<?> getAdminById (@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getAdminById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new  ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers(){
        try {
            return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-user-by-id/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.deleteUserById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        String userName = authenticationRequest.getUserName();
        String password = authenticationRequest.getPassword();

        if (userName == null || userName.isEmpty())
            return getErrorAuthorizationMessage("Заполните поле логин");

        if (password == null || password.isEmpty())
            return getErrorAuthorizationMessage("Заполните поле пароль");

        if (!userName.equals("admin") || !password.equals("admin"))
            getErrorAuthorizationMessage("Неверный логин или пароль");

        if (!userName.equals("trainer") || !password.equals("trainer"))
            getErrorAuthorizationMessage("Неверный логин или пароль");

        if (!userName.equals("client") || !password.equals("client"))
            getErrorAuthorizationMessage("Неверный логин или пароль");
        else {
            getErrorAuthorizationMessage("Неверный логин или пароль");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    private ResponseEntity<?> getErrorAuthorizationMessage(String message) {
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
