package com.example.CRM.controller;

import com.example.CRM.entity.User;
import com.example.CRM.entity.UserRole;
import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.user.CreateUserModel;
import com.example.CRM.model.user.UpdateUserModel;
import com.example.CRM.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/add-new-admin")
    public ResponseEntity<?> addNewManager(@RequestBody CreateUserModel createUserModel, UserRole userRole){
        try {
            return new ResponseEntity<>(adminService.addNewAdmin(createUserModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-admin-by-id/{id}")
    public ResponseEntity<?> getManagerById (@PathVariable Long id){
        try {
            return new ResponseEntity<>(adminService.getAdminById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new  ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-admin-by-userName")
    public ResponseEntity<?> getAdminByUserName(@RequestBody User username){
        try {
            return new ResponseEntity<>(adminService.getAdminByUserName(username), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-admin-by-email")
    public ResponseEntity<?> getAdminByEmail(@RequestBody User email){
        try {
            return new ResponseEntity<>(adminService.getAdminByEmail(email), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new  ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get-all-admins")
    public ResponseEntity<?> getAllUsers(){
        try {
            return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-admin")
    public ResponseEntity<?> updateManager(@RequestBody UpdateUserModel updateUserModel){
        try {
            return new ResponseEntity<>(adminService.updateAdmin(updateUserModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete-admin-by-userName")
    public ResponseEntity<?> deleteManagerByUserName(@RequestBody User username){
        try {
            return new ResponseEntity<>(adminService.deleteAdminByUserName(username), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete-admin-by-id/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(adminService.deleteAdminById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
