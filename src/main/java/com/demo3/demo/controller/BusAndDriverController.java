package com.demo3.demo.controller;

import com.demo3.demo.dto.BusDto;


import com.demo3.demo.dto.DriverDto;
import com.demo3.demo.model.Bus;
import com.demo3.demo.model.BusAndDriver;

import com.demo3.demo.dto.BusAndDriverDto;
import com.demo3.demo.model.Driver;
import com.demo3.demo.repository.BusAndDriverRepository;
import com.demo3.demo.repository.BusRepository;
import com.demo3.demo.repository.DriverRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@RestController
public class BusAndDriverController {
    @Autowired
    BusAndDriverRepository busAndDriverRepository;
    @Autowired
    BusRepository busRepository;
    @Autowired
    DriverRepository driverRepository;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/busAndDriver")
    public ResponseEntity<BusAndDriverDto> AssignDriverBus(@Valid @RequestBody BusAndDriverDto busAndDriverDto){
        List<Bus> bus = new ArrayList<>() ;
        bus.addAll( busRepository.findAllById(Collections.singleton(busAndDriverDto.getBusId())));
        List<Driver> driver=new ArrayList<>();
        driver.addAll( driverRepository.findAllById(Collections.singleton(busAndDriverDto.getDriverId())));
        BusAndDriver busAndDriver= new BusAndDriver(bus.get(0),driver.get(0));
       try{
            busAndDriverRepository.save(busAndDriver);
        }catch(Exception e){
               return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok().body(modelMapper.map(busAndDriver,BusAndDriverDto.class));
    }
    @GetMapping("/busAndDriver/{id}")
    public ResponseEntity<BusAndDriverDto> getBusAndDriver(@PathVariable(value = "id") Long busAndDriverId){
        BusAndDriver busAndDriver;
        BusAndDriverDto busAndDriverDto;
        try {
            busAndDriver = busAndDriverRepository.getById(busAndDriverId);
            busAndDriverDto = modelMapper.map(busAndDriver,BusAndDriverDto.class);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok().body(busAndDriverDto);
    }
    @GetMapping("/busAndDriver/findbus/{id}")
    public ResponseEntity<List<BusDto>> getBus(@PathVariable(value="id") Long driverId) {
        List<BusAndDriver> busAndDrivers = busAndDriverRepository.findByDriverId(driverId);
        List<Bus> buses = busAndDrivers.stream().map(BusAndDriver::getBus).collect(Collectors.toList());
        ListIterator<Bus> busListIterator = buses.listIterator();
        List<BusDto> busDto = new ArrayList<BusDto>();
        while (busListIterator.hasNext()) {
            busDto.add(modelMapper.map(busListIterator.next(),  BusDto.class));
        }
        System.out.println();
        return  ResponseEntity.ok().body(busDto);
    }
    @GetMapping("/busAndDriver/finddriver/{id}")
    public ResponseEntity<List<DriverDto>> getDriver(@PathVariable(value="id") Long busId){
        List<BusAndDriver> busAndDrivers = busAndDriverRepository.findByBusId(busId);
        List<Driver> drivers = busAndDrivers.stream().map(BusAndDriver::getDriver).collect(Collectors.toList());
        ListIterator<Driver> driverListIterator = drivers.listIterator();
        List<DriverDto> driverDto = new ArrayList<DriverDto>();
        while (driverListIterator.hasNext()) {
            driverDto.add(modelMapper.map(driverListIterator.next(),  DriverDto.class));
        }
        System.out.println();
        return ResponseEntity.ok().body(driverDto);
    }
}
