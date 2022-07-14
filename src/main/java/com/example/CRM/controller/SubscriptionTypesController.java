package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.SubscriptionTypesModel;
import com.example.CRM.service.SubscriptionTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionTypesController {
    @Autowired
    private SubscriptionTypesService service;

    @PostMapping("/add-subscription")
    public ResponseEntity<?> addSubscription(SubscriptionTypesModel subscriptionTypesModel){
        try {
            return new ResponseEntity<>(service.addSubscription(subscriptionTypesModel) , HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-subscription-by/{id}")
    public ResponseEntity<?> getSubscriptionById(Long id){
        try {
            return new ResponseEntity<>(service.getSubscriptionById(id),HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete-subscription-by{id}")
    public void deleteSubscriptionById(Long id){
        service.deleteSubscriptionById(id);
    }
}
