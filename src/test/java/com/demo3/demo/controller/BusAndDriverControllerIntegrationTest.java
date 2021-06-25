package com.demo3.demo.controller;

import com.demo3.demo.DemoApplication;
import com.demo3.demo.dto.BusAndDriverDto;
import com.demo3.demo.dto.BusDto;
import com.demo3.demo.model.Bus;
import com.demo3.demo.model.BusAndDriver;
import com.demo3.demo.model.Driver;
import javafx.application.Application;
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

    @Test
    public void testCreateBusAndDriver(){
        Bus bus = new Bus();
        Driver driver = new Driver();
        BusAndDriver busAndDriver = new BusAndDriver(bus,driver);
        ResponseEntity busAndDriverResponseEntity= testRestTemplate.postForEntity(getRootUrl()+"/busAndDriver",busAndDriver,BusAndDriver.class);
        assertNotNull(busAndDriverResponseEntity);
        assertNotNull(busAndDriverResponseEntity.getBody());
    }
    @Test
    public void testGetBusAndDriver(){
        assertNotNull(testRestTemplate.getForEntity(getRootUrl()+"/busAndDriver/1",BusAndDriverDto.class));
    }
    @Test
    public void testGetBusByDriverId(){
       // assertNotNull(testRestTemplate.getForEntity(getRootUrl()+"/busAndDriver/findbus/1",busDtos,HttpStatus.OK));
    }
    @Test
    public void testGetDriverByBusId(){
//        List<Driver> driver = new ArrayList<>();
//        assertNotNull(testRestTemplate.getForEntity(getRootUrl()+"/busAndDriver/finddriver/1",driver.getClass()));
    }
}
