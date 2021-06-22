package com.demo3.demo.repository;

import com.demo3.demo.model.Bus;
import com.demo3.demo.model.BusAndDriver;
import com.demo3.demo.model.Driver;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Order;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.List;

@Repository("BusAndDriverRepository")
public interface BusAndDriverRepository extends JpaRepository<BusAndDriver,Long>, JpaSpecificationExecutor<Order> {
    List<BusAndDriver> findByDriverId(long id);
    List<BusAndDriver> findByBusId(long id);
    List<BusAndDriver> findByDriverIdAndBusId(long driverId,long busId);
    //List<Bus> findByDriver_Id(long id);
}
