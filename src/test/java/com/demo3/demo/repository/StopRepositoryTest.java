package com.demo3.demo.repository;

import com.demo3.demo.model.Stop;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class StopRepositoryTest {
    @Autowired
    StopRepository stopRepository;
    @Test
    public void testSaveStop(){
        Stop stop = new Stop();
        Stop testStop = stopRepository.save(stop);
        assertNotNull(testStop);
    }
    @Test
    public void testFindAll(){
        List<Stop> stopList = new ArrayList<>();
        stopList.add(new Stop());
        stopList.add(new Stop());
        List<Stop> testStopList = stopRepository.saveAll(stopList);
        assertEquals(stopList,testStopList);
    }
    @Test
    public void testFindById(){
        Stop stop = new Stop();
        stopRepository.save(stop);
        Stop testStop = stopRepository.getById(stop.getId());
        assertEquals(stop,testStop);
    }
}