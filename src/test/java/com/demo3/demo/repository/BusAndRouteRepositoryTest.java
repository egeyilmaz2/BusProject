package com.demo3.demo.repository;

import com.demo3.demo.model.BusAndRoute;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class BusAndRouteRepositoryTest {
    @Autowired
    BusAndRouteRepository busAndRouteRepository;
    @Test
    public void testSaveBusAndRoute(){
        BusAndRoute busAndRoute = new BusAndRoute();
        BusAndRoute testBusAndRoute = busAndRouteRepository.save(busAndRoute);
        assertEquals(busAndRoute,testBusAndRoute);
    }
}