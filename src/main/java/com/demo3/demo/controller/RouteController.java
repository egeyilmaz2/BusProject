package com.demo3.demo.controller;



import com.demo3.demo.dto.RouteDto;
import com.demo3.demo.model.Route;
import com.demo3.demo.repository.RouteRepository;
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
public class RouteController {
    @Autowired
    RouteRepository routeRepository;
    ModelMapper modelMapper=new ModelMapper();
    @PostMapping("/route")
    public ResponseEntity insertRoute(@Valid @RequestBody Route route) {//try-catch her biri için
        try {
            routeRepository.save(route);
        } catch (Exception e) {
            return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/route/{id}")
    public ResponseEntity updateRoute(@PathVariable(value = "id") Long routeId, @Valid @RequestBody Route routeDetails) {
        Route route;
        try {
            route = routeRepository.getById(routeId);
            route.setName(routeDetails.getName());
        } catch (Exception e) {
            return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        routeRepository.save(route);
        return ResponseEntity.ok("UPDATE "+HttpStatus.OK);
    }

    @GetMapping("/route/{id}")
    public ResponseEntity getRouteById(@PathVariable(value="id") Long routeId) {
        RouteDto routeDto;
        try {
           routeDto = modelMapper.map(routeRepository.getById(routeId),RouteDto.class);
        }catch(Exception e){
            return (ResponseEntity) ResponseEntity.badRequest();
        }
        return ResponseEntity.ok().body(routeDto);
    }

    @DeleteMapping("/route/{id}")
    public Map<String, Boolean> deleteRoute(@PathVariable(value="id") Long routeId){
        Map<String, Boolean> response = new HashMap<>();
        Route route;
        try{
            route =  routeRepository.getById(routeId);
            routeRepository.delete(route);
        }
        catch(Exception e){
            response.put("delete action", Boolean.FALSE);
            return  response;
        }

        response.put("delete action", Boolean.TRUE);
        return response;
    }
}
