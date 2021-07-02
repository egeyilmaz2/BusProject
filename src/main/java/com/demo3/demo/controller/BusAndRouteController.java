package com.demo3.demo.controller;


import com.demo3.demo.dto.BusAndRouteDto;
import com.demo3.demo.model.*;
import com.demo3.demo.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
public class BusAndRouteController {
    @Autowired
    BusAndRouteRepository busAndRouteRepository;
    @Autowired
    BusRepository busRepository;
    @Autowired
    RouteRepository routeRepository;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/busAndRoute")
    public ResponseEntity<BusAndRouteDto> AssignRouteToBus(@Valid @RequestBody BusAndRouteDto busAndRouteDto){
        List<Bus> bus = busRepository.findAllById(Collections.singleton(busAndRouteDto.getBusId()));
        List<Route> route= routeRepository.findAllById(Collections.singleton(busAndRouteDto.getRouteId()));
        BusAndRoute busAndRoute= new BusAndRoute(bus.get(0),route.get(0));
        try{
            busAndRouteRepository.save(busAndRoute);
        }catch(Exception e){
            return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok().body(busAndRouteDto);
    }
}
