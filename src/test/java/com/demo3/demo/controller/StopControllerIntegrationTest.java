package com.demo3.demo.controller;

import com.demo3.demo.DemoApplication;
import com.demo3.demo.model.BusAndDriver;
import com.demo3.demo.model.Route;
import com.demo3.demo.model.Stop;
import com.demo3.demo.repository.StopRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StopControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    private String getRootUrl(){
        return "http://localhost:" + port;
    }
    @Autowired
    StopRepository stopRepository;

   /* @Test
    public void testCreateStop(){
        Stop stop = new Stop();
        ResponseEntity stopResponseEntity= testRestTemplate.postForEntity(getRootUrl()+"/stop",stop, Stop.class);
        assertNotNull(stopResponseEntity);
        assertNotNull(stopResponseEntity.getBody());
    }*/

    @Test
    public void testGetStopById(){
        stopRepository.save(new Stop());
        Stop stop = testRestTemplate.getForObject(getRootUrl()+"/stop/1",Stop.class);
        assertNotNull(stop);
    }

    @Test
    public void updateStop(){
        stopRepository.save(new Stop());
        Stop stop = testRestTemplate.getForObject(getRootUrl()+"/stop/1",Stop.class);
        testRestTemplate.put(getRootUrl()+"/stop/1",stop);
        Stop stopTest =testRestTemplate.getForObject(getRootUrl()+"/stop/1",Stop.class);
        assertNotNull(stopTest);
    }

    @Test
    public void testDeleteStop(){
        stopRepository.save(new Stop());
        Stop stop = testRestTemplate.getForObject(getRootUrl()+"/stop/1",Stop.class);
        assertNotNull(stop);
        testRestTemplate.delete(getRootUrl()+"/stop/1");
        try {
            stop = testRestTemplate.getForObject(getRootUrl() + "/stop/1", Stop.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}