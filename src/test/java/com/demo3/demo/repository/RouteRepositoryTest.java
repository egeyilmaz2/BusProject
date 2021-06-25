package com.demo3.demo.repository;

import com.demo3.demo.model.Route;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class RouteRepositoryTest {
    @Autowired
    RouteRepository routeRepository;
    @Test
    public void testInsertRoute(){
        Route route = new Route();
        route.setName("route1");
        assertNotNull(route);
        routeRepository.save(route);
    }


}