package com.demo3.demo.controller;

import com.demo3.demo.DemoApplication;
import com.demo3.demo.model.Bus;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DriverControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    private String getRootUrl(){
        return "http://localhost:" + port;
    }

    @Test
    public void testCreateDriver(){
        Driver driver = new Driver();
        driver.setName("Hamza");
        ResponseEntity<Driver> driverResponseEntity = testRestTemplate.postForEntity(getRootUrl() +"/driver",driver,Driver.class);
        assertNotNull(driverResponseEntity);
        assertNotNull(driverResponseEntity.getBody());
    }
    @Test
    public void testGetDriverById(){
        Driver driver = testRestTemplate.getForObject(getRootUrl()+"/driver/1",Driver.class);
        System.out.println(driver.getName());
        assertNotNull(driver);
    }
    @Test
    public void updateDriver(){
        Driver driver = testRestTemplate.getForObject(getRootUrl()+"/driver/1",Driver.class);
        driver.setName("Ertuğrul");
        testRestTemplate.put(getRootUrl()+"/driver/1",driver);
        Driver updatedDriver =testRestTemplate.getForObject(getRootUrl()+"/driver/1",Driver.class);
        assertNotNull(updatedDriver);
    }
    @Test
    public void testDeleteDriver(){
        Driver driver = testRestTemplate.getForObject(getRootUrl()+"/driver/1",Driver.class);
        assertNotNull(driver);
        testRestTemplate.delete(getRootUrl()+"/driver/1");
        try {
            driver = testRestTemplate.getForObject(getRootUrl() + "/driver/1", Driver.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
