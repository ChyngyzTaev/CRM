package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.RoleModel;
import com.example.CRM.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-role")
public class RoleController {
    @Autowired
    private RoleService service;

    @PostMapping("/add-new-role")
    public ResponseEntity<?> addNewRole(@RequestBody RoleModel roleModel){
        try {
            return new ResponseEntity<>(service.addNewRole(roleModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-role-by-id/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getRoleById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-role")
    public ResponseEntity<?> getAllRole(){
        try {
            return new ResponseEntity<>(service.getAllRole(),HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-role-by-id/{id}")
    public ResponseEntity<?> deleteRoleById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.deleteRoleById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
