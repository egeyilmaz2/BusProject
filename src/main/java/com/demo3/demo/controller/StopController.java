package com.demo3.demo.controller;

import com.demo3.demo.dto.StopDto;
import com.demo3.demo.model.Route;
import com.demo3.demo.model.Stop;
import com.demo3.demo.repository.RouteRepository;
import com.demo3.demo.repository.StopRepository;
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
public class StopController {
    @Autowired
    StopRepository stopRepository;
    ModelMapper modelMapper=new ModelMapper();
    @PostMapping("/stop")
    public ResponseEntity<StopDto> insertStop(@Valid @RequestBody Stop stop) {//try-catch her biri için
        StopDto stopDto;
        try {
            stopDto = modelMapper.map(stopRepository.save(stop),StopDto.class);
        } catch (Exception e) {
            return (ResponseEntity<StopDto>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(stopDto);
    }

    @PutMapping("/stop/{id}")
    public ResponseEntity updateStop(@PathVariable(value = "id") Long stopId, @Valid @RequestBody Stop stopDetails) {
        Stop stop;
        try {
            stop = stopRepository.getById(stopId);
            stop.setStopNo(stopDetails.getStopNo());
        } catch (Exception e) {
            return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        stopRepository.save(stop);
        return ResponseEntity.ok("UPDATE "+HttpStatus.OK);
    }

    @GetMapping("/stop/{id}")
    public ResponseEntity<StopDto> getStopById(@PathVariable(value="id") Long stopId) {
        StopDto stopDto;
        try {
            stopDto =  modelMapper.map(stopRepository.getById(stopId),StopDto.class);
        }catch(Exception e){
            return (ResponseEntity) ResponseEntity.badRequest();
        }
        return ResponseEntity.ok().body(stopDto);
    }

    @DeleteMapping("/stop/{id}")
    public Map<String, Boolean> deleteStop(@PathVariable(value="id") Long stopId){
        Map<String, Boolean> response = new HashMap<>();
        Stop stop;
        try{
            stop =  stopRepository.getById(stopId);
            stopRepository.delete(stop);
        }
        catch(Exception e){
            response.put("delete action", Boolean.FALSE);
            return  response;
        }

        response.put("delete action", Boolean.TRUE);
        return response;
    }
}
