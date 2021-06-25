package com.demo3.demo.repository;

import com.demo3.demo.model.Driver;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class DriverRepositoryTest {
    @Autowired
    DriverRepository driverRepository;
    @Test
    public void testInsertDriver(){
        Driver driver = new Driver();
        Driver testSaveDriver = driverRepository.save(driver);
        assertNotNull(testSaveDriver);
    }
    @Test
    public void testGetDriver(){
        Driver driver = new Driver();
        Driver testSave=driverRepository.save(driver);
        assertNotNull(testSave);
        Driver driverGetTest=driverRepository.getById(driver.getId());
        assertNotNull(driverGetTest);
    }
    @Test
    public void testFindAllDriver(){
        Driver driver = new Driver();
        driver.setName("Osman");
        Driver driver2 = new Driver();
        driver2.setName("Mahmut");
        Driver testSave1 = driverRepository.save(driver);
        Driver testSave2 = driverRepository.save(driver2);
        assertNotNull(testSave1);
        assertNotNull(testSave2);
        List<Driver> driverList=driverRepository.findAll();
        assertNotNull(driverList);
    }
}