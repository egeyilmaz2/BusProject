package com.demo3.demo.controller;
import com.demo3.demo.dto.BusAndRouteDto;
import com.demo3.demo.dto.BusDto;
import com.demo3.demo.model.Bus;
import com.demo3.demo.model.BusAndRoute;
import com.demo3.demo.model.Route;
import com.demo3.demo.repository.BusAndRouteRepository;
import com.demo3.demo.repository.BusRepository;
import com.demo3.demo.repository.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class BusController {
    @Autowired
    BusRepository busRepository;
    @Autowired
    BusAndRouteRepository busAndRouteRepository;
    @Autowired
    RouteRepository routeRepository;
    ModelMapper modelMapper=new ModelMapper();
    @PostMapping("/bus")
    public ResponseEntity<Bus> insertBus(@Valid @RequestBody Bus bus) {//try-catch her biri için
        try {
            busRepository.save(bus);
        } catch (Exception e) {
            return (ResponseEntity<Bus>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(bus);
    }

    @PostMapping("/bus/assignBusToRoute")
    public ResponseEntity<BusAndRouteDto> AssingBusToRoute(@Valid @RequestBody BusAndRouteDto busAndRouteDto) {
        try {
            BusAndRoute busAndRoute= new BusAndRoute(busRepository.getById(busAndRouteDto.getBusId()),routeRepository.getById(busAndRouteDto.getRouteId()));
            busAndRouteRepository.save(busAndRoute);
        } catch (Exception e) {
            return (ResponseEntity<BusAndRouteDto>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(busAndRouteDto);
    }
    @PutMapping("/bus/{id}")
    public ResponseEntity updateBus(@PathVariable(value = "id") Long BusId,@Valid @RequestBody Bus busDetails) {
        Bus bus;
        try {
            bus = busRepository.getById(BusId);
            bus.setNo(busDetails.getNo());
        } catch (Exception e) {
             return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        busRepository.save(bus);
        return ResponseEntity.ok("UPDATE "+HttpStatus.OK);
    }

    @GetMapping("/bus/{id}")
    public ResponseEntity<BusDto> getBusById(@PathVariable(value="id") Long busId) {
        BusDto busDto;
        try {
           busDto = modelMapper.map(busRepository.getById(busId),BusDto.class);
        }catch(Exception e){
            return (ResponseEntity) ResponseEntity.badRequest();
        }
        return ResponseEntity.ok().body(busDto);
    }

    @DeleteMapping("/bus/{id}")
    public Map<String, Boolean> deleteBus(@PathVariable(value="id") Long BusId){
        Map<String, Boolean> response = new HashMap<>();
        Bus bus;
        try{
            bus =  busRepository.getById(BusId);
            busRepository.delete(bus);
        }
        catch(Exception e){
            response.put("delete action", Boolean.FALSE);
            return  response;
        }

        response.put("delete action", Boolean.TRUE);
        return response;
    }



}
