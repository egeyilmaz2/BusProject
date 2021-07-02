package com.demo3.demo.controller;

import com.demo3.demo.DemoApplication;
import com.demo3.demo.dto.RouteDto;
import com.demo3.demo.model.Bus;
import com.demo3.demo.model.BusAndDriver;
import com.demo3.demo.model.Route;
import com.demo3.demo.repository.RouteRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouteControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    private String getRootUrl(){
        return "http://localhost:" + port;
    }
    @Autowired
    RouteRepository routeRepository;

    @Test
    public void testCreateRoute(){
        Route route = new Route();
        ResponseEntity<RouteDto> routeDtoResponseEntity= testRestTemplate.postForEntity(getRootUrl()+"/route",route, RouteDto.class);
        assertNotNull(routeDtoResponseEntity);
    }
    @Test
    public void testGetRouteById(){
        routeRepository.save(new Route());
        Route route = testRestTemplate.getForObject(getRootUrl()+"/route/1",Route.class);
        assertNotNull(route);
    }
    @Test
    public void updateRoute(){
        Route route = routeRepository.save(new Route());
        testRestTemplate.put(getRootUrl() + "/route/" + route.getId(), new Route());
        assertNotNull(routeRepository.getById(route.getId()));
    }
    @Test
    public void testDeleteRoute(){
        routeRepository.save(new Route());
        Route route = testRestTemplate.getForObject(getRootUrl()+"/route/1",Route.class);
        assertNotNull(route);
        testRestTemplate.delete(getRootUrl()+"/route/1");
        try {
            route = testRestTemplate.getForObject(getRootUrl() + "/route/1", Route.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
