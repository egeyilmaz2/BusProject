package com.demo3.demo.controller;

import com.demo3.demo.DemoApplication;
import com.demo3.demo.dto.BusAndDriverDto;
import com.demo3.demo.dto.BusDto;
import com.demo3.demo.dto.DriverDto;
import com.demo3.demo.model.Bus;
import com.demo3.demo.model.BusAndDriver;
import com.demo3.demo.model.Driver;
import com.demo3.demo.repository.BusAndDriverRepository;
import com.demo3.demo.repository.BusRepository;
import com.demo3.demo.repository.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusAndDriverControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    private String getRootUrl(){
        return "http://localhost:" + port;
    }
    @Autowired
    BusAndDriverRepository busAndDriverRepository;
    @Autowired
    BusRepository busRepository;
    @Autowired
    DriverRepository driverRepository;

    @Test
    public void testCreateBusAndDriver(){
        assertNotNull(testRestTemplate.postForEntity(getRootUrl()+"/busAndDriver",new BusAndDriverDto(),BusAndDriverDto.class));
    }

    @Test
    public void testGetBusAndDriver(){
        BusAndDriver busAndDriver = busAndDriverRepository.save(new BusAndDriver());
        assertNotNull(testRestTemplate.getForEntity(getRootUrl()+"/busAndDriver/"+busAndDriver.getId(),BusAndDriverDto.class));
    }

    @Test
    public void testGetBusByDriverId(){
        List<BusDto> busDtos = new ArrayList<>();
        Driver driver = driverRepository.save(new Driver());
        BusAndDriver busAndDriver = busAndDriverRepository.save(new BusAndDriver(busRepository.save(new Bus()),driver));
        assertNotNull(testRestTemplate.getForEntity(getRootUrl()+"/busAndDriver/findbus/"+driver.getId(),busDtos.getClass()));
    }
    @Test
    public void testGetDriverByBusId(){
        List<DriverDto> driverDtos = new ArrayList<>();
        Bus bus = busRepository.save(new Bus());
        BusAndDriver busAndDriver = busAndDriverRepository.save(new BusAndDriver(bus,driverRepository.save(new Driver())));
        assertNotNull(testRestTemplate.getForEntity(getRootUrl()+"/busAndDriver/finddriver/"+bus.getId(),driverDtos.getClass()));
    }
}
