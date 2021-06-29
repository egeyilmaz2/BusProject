package com.demo3.demo.controller;

import com.demo3.demo.DemoApplication;
import com.demo3.demo.dto.DriverDto;
import com.demo3.demo.model.Bus;
import com.demo3.demo.model.Driver;
import com.demo3.demo.repository.DriverRepository;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class DriverControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private HttpHeaders httpHeaders;
    @LocalServerPort
    private int port;
    private String getRootUrl(){
        return "http://localhost:" + port;
    }
    @Autowired
    DriverRepository driverRepository;

    @Test
    public void testCreateDriver(){
        Driver driver = new Driver();
        ResponseEntity<Driver> driverResponseEntity = testRestTemplate.postForEntity(getRootUrl() +"/driver",driver,Driver.class);
        assertNotNull(driverResponseEntity);
        assertNotNull(driverResponseEntity.getBody());
    }

    @Test
    public void testGetDriverById(){
        Driver driver =driverRepository.save(new Driver());
        assertNotNull(testRestTemplate.getForObject(getRootUrl()+"/driver/"+driver.getId(),Driver.class));
    }

    @Test
    public void updateDriver(){
        Driver driver = driverRepository.save(new Driver());
        Driver updatedDriver= new Driver();
        testRestTemplate.put(getRootUrl()+"/driver/"+driver.getId(),updatedDriver);
        assertNotNull(testRestTemplate.getForObject(getRootUrl()+"/driver/"+driver.getId(),Driver.class));
    }
    @Test
    public void testDeleteDriver(){
        Driver driverSave = driverRepository.save(new Driver());
        Driver driver = testRestTemplate.getForObject(getRootUrl()+"/driver/"+driverSave.getId(),Driver.class);
        assertNotNull(driver);
        testRestTemplate.delete(getRootUrl()+"/driver/"+driver.getId());
        try {
            driver = testRestTemplate.getForObject(getRootUrl() + "/driver/"+driver.getId(), Driver.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    @Test//Httpheader ile test bir JSON çıktısı beklenir
    public void testGetDriver() throws Exception {
        Driver driver = driverRepository.save(new Driver());
        HttpEntity<String> entity = new HttpEntity<>(null,httpHeaders);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl()+"/driver/"+driver.getId(), HttpMethod.GET,entity,String.class);
        assertNotNull(response);
    }
}
