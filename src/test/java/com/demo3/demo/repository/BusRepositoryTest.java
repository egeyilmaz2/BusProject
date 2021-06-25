package com.demo3.demo.repository;

import com.demo3.demo.model.Bus;
import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class BusRepositoryTest {
    @Autowired
    BusRepository busRepository;

    @Test
    public void testBusInsert() throws Exception{
        Bus bus = new Bus();
        bus.setNo("1000");
        Bus testSavedBus= busRepository.save(bus);
        assertEquals(testSavedBus.getNo(),bus.getNo());
    }

    @Test
    public void testGetBus() throws Exception{
        Bus bus = new Bus();
        bus.setNo("1000");
        busRepository.save(bus);
        Bus getBusTest = busRepository.getById(bus.getId());
        assertNotNull(getBusTest);
    }

    @Test
    public void testBusFindAll() throws Exception{
        Bus bus1 = new Bus();
        bus1.setNo("1000");
        Bus bus2 = new Bus();
        bus1.setNo("1000");
        busRepository.save(bus1);
        busRepository.save(bus2);
        List<Bus> busList = busRepository.findAll();
        assertNotNull(busList);
    }
    @Test
    public void testDeleteBus() throws  Exception{
        Bus bus = new Bus();
        bus.setNo("1000");
        busRepository.save(bus);
        assertNotNull(bus);
        busRepository.deleteById(bus.getId());
    }
}