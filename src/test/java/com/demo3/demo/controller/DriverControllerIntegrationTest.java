package com.demo3.demo.controller;

import com.demo3.demo.DemoApplication;
import com.demo3.demo.dto.DriverDto;
import com.demo3.demo.model.Driver;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DriverControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    DriverRepository driverRepository;

    private String getRootUrl(){
        return "http://localhost:" + port;
    }
    @Test
    public void testCreateDriver() throws Exception{
        Driver driver = new Driver();
        ResponseEntity<Driver> driverResponseEntity = testRestTemplate.postForEntity(getRootUrl() +"/driver",driver,Driver.class);
        assertNotNull(driverResponseEntity);
        assertNotNull(driverResponseEntity.getBody());
    }
    @Test
    public void testGetDriverById(){
        Driver driver = driverRepository.save(new Driver());
        assertNotNull(testRestTemplate.getForObject(getRootUrl()+"/driver/"+driver.getId(), DriverDto.class));
    }
    @Test
    public void updateDriver(){
        Driver driver = driverRepository.save(new Driver());
        Driver driverPut = driverRepository.save(new Driver());
        testRestTemplate.put(getRootUrl()+"/driver/"+driver.getId(),driverPut);
        Driver updatedDriver =testRestTemplate.getForObject(getRootUrl()+"/driver/"+driver.getId(),Driver.class);
        assertNotNull(updatedDriver);
    }
    @Test
    public void testDeleteDriver(){
        Driver driver = driverRepository.save(new Driver());
        testRestTemplate.delete(getRootUrl()+"/driver/"+driver.getId());
        try {
            driver = testRestTemplate.getForObject(getRootUrl() + "/driver/"+driver.getId(), Driver.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}