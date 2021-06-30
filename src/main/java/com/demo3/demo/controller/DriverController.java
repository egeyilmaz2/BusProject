package com.demo3.demo.controller;

import com.demo3.demo.dto.DriverDto;
import com.demo3.demo.model.Bus;
import com.demo3.demo.model.Driver;
import com.demo3.demo.repository.BusRepository;
import com.demo3.demo.repository.DriverRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController

public class DriverController {
    @Autowired
    DriverRepository driverRepository;
    ModelMapper modelMapper = new ModelMapper();
    @PostMapping("/driver")
    public ResponseEntity insertDriver(@Valid @RequestBody Driver driver) {//try-catch her biri için
        try {
            driverRepository.save(driver);
        } catch (Exception e) {
            return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(driver);
    }

    @PutMapping("/driver/{id}")
    public ResponseEntity updateDriver(@PathVariable(value = "id") Long driverId, @Valid @RequestBody Driver driverDetails) {
        Driver driver;
        try {
            driver = driverRepository.getById(driverId);
            driver.setName(driverDetails.getName());
        } catch (Exception e) {
            return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        driverRepository.save(driver);
        return ResponseEntity.ok("UPDATE "+HttpStatus.OK);
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable(value="id") Long driverId) {
        DriverDto driverDto;
        try {
            driverDto =  modelMapper.map(driverRepository.getById(driverId),DriverDto.class);
        }catch(Exception e){
            return (ResponseEntity) ResponseEntity.badRequest();
        }
        return ResponseEntity.ok().body(driverDto);
    }

    @DeleteMapping("/driver/{id}")
    public Map<String, Boolean> deleteBus(@PathVariable(value="id") Long driverId){
        Map<String, Boolean> response = new HashMap<>();
        Driver driver;
        try{
            driver =  driverRepository.getById(driverId);
            driverRepository.delete(driver);
        }
        catch(Exception e){
            response.put("delete action", Boolean.FALSE);
            return  response;
        }

        response.put("delete action", Boolean.TRUE);
        return response;
    }

}
