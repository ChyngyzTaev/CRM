package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.subscriptionTypes.CreateSubscriptionTypesModel;
import com.example.CRM.model.subscriptionTypes.SubscriptionTypesModel;
import com.example.CRM.model.subscriptionTypes.UpdateSubscriptionTypesModel;
import com.example.CRM.service.SubscriptionTypesService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionTypesController {
    @Autowired
    private SubscriptionTypesService service;

    @PostMapping("/add-new-subscription")
    public ResponseEntity<?> addSubscription(@RequestBody CreateSubscriptionTypesModel subscriptionTypesModel){
        try {
            return new ResponseEntity<>(service.addSubscription(subscriptionTypesModel) , HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-subscription-by-id/{id}")
    public ResponseEntity<?> getSubscriptionById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getSubscriptionById(id),HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-subscription")
    public ResponseEntity<?> updateSubscription(@RequestBody UpdateSubscriptionTypesModel subscriptionTypesModel){
        try {
            return new ResponseEntity<>(service.updateSubscription(subscriptionTypesModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-subscription-by-id/{id}")
    public ResponseEntity<?> deleteSubscriptionById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.deleteSubscriptionById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
