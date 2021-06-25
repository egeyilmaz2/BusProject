package com.demo3.demo.repository;

import com.demo3.demo.model.Bus;
import com.demo3.demo.model.BusAndDriver;
import com.demo3.demo.model.Driver;
import com.demo3.demo.repository.BusAndDriverRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        BusAndDriver testBusAndDriver=busAndDriverRepository.save(busAndDriver);
        assertEquals(busAndDriver,testBusAndDriver);
    }
    @Test
    public void testGetByIdBusAndDriver(){
        Bus bus = new Bus();
        Driver driver = new Driver();
        BusAndDriver busAndDriver = new BusAndDriver(bus,driver);
        busAndDriverRepository.save(busAndDriver);
        BusAndDriver testBusAndDriver = busAndDriverRepository.getById(busAndDriver.getId());
        assertEquals(busAndDriver,testBusAndDriver);
    }
    @Test
    public void testFindAllBusAndDriver(){
        List<BusAndDriver> busAndDriverList = new ArrayList<>();
        busAndDriverList.add(new BusAndDriver(new Bus(),new Driver()));
        busAndDriverList.add(new BusAndDriver(new Bus(),new Driver()));
        busAndDriverRepository.saveAll(busAndDriverList);
        List<BusAndDriver> testBusAndDriversList = busAndDriverRepository.findAll();
        assertArrayEquals(busAndDriverList.stream().toArray(),testBusAndDriversList.stream().toArray());
    }

}
