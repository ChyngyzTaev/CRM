package com.example.CRM.controller;

import com.example.CRM.exception.BadRequestException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.chart.CreateChartModel;
import com.example.CRM.model.chart.UpdateChartModel;
import com.example.CRM.service.ChartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chart")
public class ChartController {
    @Autowired
    private ChartService chartService;

    @PostMapping("/add-new-chart")
    public ResponseEntity<?> addNewChart(@RequestBody CreateChartModel chartModel){
        try {
            return new ResponseEntity<>(chartService.addNewChart(chartModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-chart-by-id/{id}")
    public ResponseEntity<?> getChartById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(chartService.getChartById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-chart")
    public ResponseEntity<?> getAllChart(){
        try {
            return new ResponseEntity<>(chartService.getAllChart(),HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-chart")
    public ResponseEntity<?> updateChart(@RequestBody UpdateChartModel chartModel){
        try {
            return new ResponseEntity<>(chartService.updateChart(chartModel), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-chart-by-id/{id}")
    public ResponseEntity<?> deleteChartById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(chartService.deleteChartById(id), HttpStatus.OK);
        }catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
