package com.demo3.demo.repository;

import com.demo3.demo.model.Driver;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("DriverRepository")

public interface DriverRepository extends JpaRepository<Driver,Long> , JpaSpecificationExecutor<Order> {

}
