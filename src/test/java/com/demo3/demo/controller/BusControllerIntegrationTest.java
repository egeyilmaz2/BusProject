package com.demo3.demo.controller;

import com.demo3.demo.DemoApplication;
import com.demo3.demo.model.Bus;
import com.demo3.demo.repository.BusRepository;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    BusRepository busRepository;

    private String getRootUrl(){
        return "http://localhost:" + port;
    }
    @Test
    public void testCreateBus() throws Exception{
        Bus bus = new Bus();
        bus.setNo("1000");
        ResponseEntity<Bus> busResponseEntity = testRestTemplate.postForEntity(getRootUrl() +"/bus",bus,Bus.class);
        assertNotNull(busResponseEntity);
        assertNotNull(busResponseEntity.getBody());
    }
    @Test
    public void testGetBusById(){
        busRepository.save(new Bus());
        Bus bus = testRestTemplate.getForObject(getRootUrl()+"/bus/1",Bus.class);
        assertNotNull(bus);
    }
    @Test
    public void updateBus(){
        busRepository.save(new Bus());
        Bus bus= testRestTemplate.getForObject(getRootUrl()+"/bus/1",Bus.class);
        bus.setNo("1001");
        testRestTemplate.put(getRootUrl()+"/bus/1",bus);
        Bus updatedBus =testRestTemplate.getForObject(getRootUrl()+"/bus/1",Bus.class);
        assertNotNull(updatedBus);
    }
    @Test
    public void testDeleteBus(){
        busRepository.save(new Bus());
        Bus bus = testRestTemplate.getForObject(getRootUrl()+"/bus/1",Bus.class);
        assertNotNull(bus);
        testRestTemplate.delete(getRootUrl()+"/bus/1");
        try {
            bus = testRestTemplate.getForObject(getRootUrl() + "/bus/1", Bus.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
