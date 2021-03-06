package com.demo3.demo.controller;

import com.demo3.demo.DemoApplication;
import com.demo3.demo.dto.BusAndRouteDto;
import com.demo3.demo.dto.BusDto;
import com.demo3.demo.model.Bus;
import com.demo3.demo.model.BusAndRoute;
import com.demo3.demo.model.Route;
import com.demo3.demo.repository.BusAndRouteRepository;
import com.demo3.demo.repository.BusRepository;
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
        ResponseEntity<Bus> busResponseEntity = testRestTemplate.postForEntity(getRootUrl() +"/bus",bus,Bus.class);
        assertNotNull(busResponseEntity);
        assertNotNull(busResponseEntity.getBody());
    }
    @Test
    public void testGetBusById(){
        Bus bus = busRepository.save(new Bus());
        assertNotNull(testRestTemplate.getForObject(getRootUrl()+"/bus/"+bus.getId(), BusDto.class));
    }
    @Test
    public void updateBus(){
        Bus bus = busRepository.save(new Bus());
        Bus busPut = busRepository.save(new Bus());
        testRestTemplate.put(getRootUrl()+"/bus/"+bus.getId(),busPut);
        Bus updatedBus =testRestTemplate.getForObject(getRootUrl()+"/bus/"+bus.getId(),Bus.class);
        assertNotNull(updatedBus);
    }
    @Test
    public void testDeleteBus(){
        Bus bus = busRepository.save(new Bus());
        testRestTemplate.delete(getRootUrl()+"/bus/"+bus.getId());
        try {
            bus = testRestTemplate.getForObject(getRootUrl() + "/bus/"+bus.getId(), Bus.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
    @Test
    public void testAssignBusToRoute(){
        ResponseEntity responseEntity = testRestTemplate.postForEntity(getRootUrl()+"/bus/assignBusToRoute", new BusAndRouteDto(),BusAndRouteDto.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
    }
}
