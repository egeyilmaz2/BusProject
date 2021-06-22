package com.demo3.demo.controller;


import com.demo3.demo.dto.RouteAndStopDto;
import com.demo3.demo.model.*;
import com.demo3.demo.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
public class RouteAndStopController {
    @Autowired
    RouteAndStopRepository routeAndStopRepository;
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    StopRepository stopRepository;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/routeAndStop")
    public ResponseEntity AssignStopToRoute(@Valid @RequestBody RouteAndStopDto routeAndStopDto){
        List<Route> route = routeRepository.findAllById(Collections.singleton(routeAndStopDto.getRouteId()));
        List<Stop> stop= stopRepository.findAllById(Collections.singleton(routeAndStopDto.getStopId()));
        RouteAndStop routeAndStop= new RouteAndStop(route.get(0),stop.get(0));
        try{
            routeAndStopRepository.save(routeAndStop);
        }catch(Exception e){
            return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(HttpStatus.CREATED);
    }
   /* @GetMapping("/routeAndStop/findroute/{id}")
    public ResponseEntity<List<RouteDto>> getRoute(@PathVariable(value="id") Long stopId) {
        List<BusAndDriver> busAndDrivers = routeAndStopRepository.findByStopID(stopId);
        List<Bus> buses = busAndDrivers.stream().map(BusAndDriver::getBus).collect(Collectors.toList());
        ListIterator<Bus> busListIterator = buses.listIterator();
        List<BusDto> busDto = new ArrayList<BusDto>();
        while (busListIterator.hasNext()) {
            busDto.add(modelMapper.map(busListIterator.next(),  BusDto.class));
        }
        System.out.println();
        return new ResponseEntity<>(busDto,HttpStatus.OK);
    }
    @GetMapping("/routeAndStop/findstop/{id}")
    public ResponseEntity<List<DriverDto>> getRoute(@PathVariable(value="id") Long busId){
        List<BusAndDriver> busAndDrivers = busAndDriverRepository.findByBusId(busId);
        List<Driver> drivers = busAndDrivers.stream().map(BusAndDriver::getDriver).collect(Collectors.toList());
        ListIterator<Driver> driverListIterator = drivers.listIterator();
        List<DriverDto> driverDto = new ArrayList<DriverDto>();
        while (driverListIterator.hasNext()) {
            driverDto.add(modelMapper.map(driverListIterator.next(),  DriverDto.class));
        }
        System.out.println();
        return new ResponseEntity<>(driverDto,HttpStatus.OK);
    }*/
}
