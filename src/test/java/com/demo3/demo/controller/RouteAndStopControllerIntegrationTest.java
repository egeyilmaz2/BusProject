package com.demo3.demo.controller;

import com.demo3.demo.DemoApplication;
import com.demo3.demo.dto.RouteAndStopDto;
import com.demo3.demo.model.*;
import com.demo3.demo.repository.RouteAndStopRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouteAndStopControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    private String getRootUrl(){
        return "http://localhost:" + port;
    }
    @Autowired
    RouteAndStopRepository routeAndStopRepository;

    @Test
    public void testCreateBusAndRoute(){
        ResponseEntity RouteAndStopResponseEntity= testRestTemplate.postForEntity(getRootUrl()+"/routeAndStop",new RouteAndStopDto(),RouteAndStopDto.class);
        assertNotNull(RouteAndStopResponseEntity);
        assertNotNull(RouteAndStopResponseEntity.getBody());
    }
}