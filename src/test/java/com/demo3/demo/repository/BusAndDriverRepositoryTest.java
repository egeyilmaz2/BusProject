package com.demo3.demo.repository;

import com.demo3.demo.model.Bus;
import com.demo3.demo.model.BusAndDriver;
import com.demo3.demo.model.Driver;
import com.demo3.demo.repository.BusAndDriverRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class BusAndDriverRepositoryTest {
    @Autowired
    BusAndDriverRepository busAndDriverRepository;
    @Test
    public void testInsertBusAndDriver(){
        Bus bus = new Bus();
        Driver driver = new Driver();
        BusAndDriver busAndDriver = new BusAndDriver(bus,driver);
        assertNotNull(busAndDriver);
        busAndDriverRepository.save(busAndDriver);
    }
    @Test
    public void testGetByIdBusAndDriver(){
        Bus bus = new Bus();
        Driver driver = new Driver();
        BusAndDriver busAndDriver = new BusAndDriver(bus,driver);
        assertNotNull(busAndDriver);
        busAndDriverRepository.save(busAndDriver);
        BusAndDriver testBusAndDriver = busAndDriverRepository.getById(busAndDriver.getId());
        assertNotNull(testBusAndDriver);
    }
    @Test
    public void testFindAllBusAndDriver(){
        Bus bus = new Bus();
        Driver driver = new Driver();
        BusAndDriver busAndDriver = new BusAndDriver(bus,driver);
        assertNotNull(busAndDriver);
        busAndDriverRepository.save(busAndDriver);
        BusAndDriver busAndDriver2 = new BusAndDriver(bus,driver);
        assertNotNull(busAndDriver2);
        busAndDriverRepository.save(busAndDriver2);
        List<BusAndDriver> busAndDriversList = busAndDriverRepository.findAll();
        assertNotNull(busAndDriversList);
    }

}
