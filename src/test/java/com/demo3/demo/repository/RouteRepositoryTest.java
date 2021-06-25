package com.demo3.demo.repository;

import com.demo3.demo.model.Route;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class RouteRepositoryTest {
    @Autowired
    RouteRepository routeRepository;
    @Test
    public void testInsertRoute(){
        Route route = new Route();
        Route testRoute = routeRepository.save(route);
        assertNotNull(testRoute);
    }
    @Test
    public void testFindAllRoute(){
        List<Route> testRouteList = new ArrayList<>();
        testRouteList.add(new Route());
        testRouteList.add(new Route());
        testRouteList.add(new Route());
        assertNotNull(testRouteList);
        routeRepository.saveAll(testRouteList);
        List<Route> testFindrouteList = routeRepository.findAll();
        assertNotNull(testFindrouteList);
    }
    @Test
    public void testGetRoute(){
        Route route = new Route();
        route.setName("testRoute");
        assertNotNull(route);
        routeRepository.save(route);
        Route testRoute = routeRepository.getById(route.getId());
        assertNotNull(testRoute);
    }


}