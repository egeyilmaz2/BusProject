package com.demo3.demo.repository;


import com.demo3.demo.model.Route;
import com.demo3.demo.model.RouteAndStop;
import com.demo3.demo.model.Stop;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class RouteAndStopRepositoryTest {
    @Autowired
    RouteAndStopRepository routeAndStopRepository;

    @Test
    public void testRouteAndSTopSave() throws Exception{
        RouteAndStop routeAndStop = new RouteAndStop(new Route(),new Stop());
        RouteAndStop testSave = routeAndStopRepository.save(routeAndStop);
        assertEquals(routeAndStop.getId(),testSave.getId());
    }

    @Test
    public void testGetRouteAndStop() throws Exception{
        RouteAndStop routeAndStop = new RouteAndStop(new Route(),new Stop());
        routeAndStopRepository.save(routeAndStop);
        RouteAndStop testGet = routeAndStopRepository.getById(routeAndStop.getId());
        assertEquals(routeAndStop.getId(),testGet.getId());
    }

 /*   @Test
    public void testRouteAndStopFindAll() throws Exception{
        RouteAndStop routeAndStop = new RouteAndStop(new Route(),new Stop());
        routeAndStopRepository.save(routeAndStop);
         routeAndStop = new RouteAndStop(new Route(),new Stop());
        routeAndStopRepository.save(routeAndStop);
        List<RouteAndStop> testFindAll= routeAndStopRepository.findAll();
        assertNotNull(testFindAll);
    }*/
  /*  @Test
    public void testRouteAndStopDelete() throws  Exception{
        RouteAndStop routeAndStop = new RouteAndStop(new Route(),new Stop());
        routeAndStop=routeAndStopRepository.save(routeAndStop);
        routeAndStopRepository.deleteById(routeAndStop.getId());
        assertFalse(routeAndStopRepository.existsById(routeAndStop.getId()));
    }*/
}