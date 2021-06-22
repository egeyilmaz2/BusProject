package com.demo3.demo.repository;


import com.demo3.demo.model.BusAndRoute;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("BusAndRouteRepository")
public interface BusAndRouteRepository extends JpaRepository<BusAndRoute,Long>, JpaSpecificationExecutor<Order> {
    List<BusAndRoute> findByBusIdAndRouteId(long busId,long routeId);
}
