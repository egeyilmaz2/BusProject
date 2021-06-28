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

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class DriverRepositoryTest {
    @Autowired
    DriverRepository driverRepository;
    @Test
    public void testInsertDriver()throws Exception{
        Driver driver = new Driver();
        Driver testSaveDriver = driverRepository.save(driver);
        assertNotNull(testSaveDriver);
    }
    @Test
    public void testGetDriver()throws Exception{
        Driver driver = new Driver();
        Driver testSave=driverRepository.save(driver);
        assertNotNull(testSave);
        Driver driverGetTest=driverRepository.getById(driver.getId());
        assertNotNull(driverGetTest);
    }
   /* @Test
    public void testFindAllDriver() throws Exception{
        Driver driver = new Driver();
        Driver driver2 = new Driver();
        driverRepository.save(driver);
        driverRepository.save(driver2);
        List<Driver> driverList = driverRepository.findAll();
        assertNotNull(driverList);
    }*/
}