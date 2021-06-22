package com.demo3.demo.repository;


import com.demo3.demo.model.Bus;
import com.demo3.demo.model.BusAndDriver;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("BusRepository")

public interface BusRepository extends JpaRepository<Bus,Long> , JpaSpecificationExecutor<Order> {


   // List<Bus> findAllByBusAndDrivers(List<BusAndDriver> busAndDriver);
    /*
    @Modifying
    @Query(
            value =
                    "insert into Bus (no ,status) values (:no, :status)",
            nativeQuery = true)
    void insertBus(@Param("no") String no,@Param("no") boolean status);*/
}
