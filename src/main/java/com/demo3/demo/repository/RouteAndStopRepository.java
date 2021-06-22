package com.demo3.demo.repository;


import com.demo3.demo.model.RouteAndStop;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("RouteAndStopRepository")
public interface RouteAndStopRepository  extends JpaRepository<RouteAndStop,Long>, JpaSpecificationExecutor<Order> {
}
