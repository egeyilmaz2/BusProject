package com.demo3.demo.repository;

import com.demo3.demo.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Order;

@Repository("RouteRepository")
public interface RouteRepository extends JpaRepository<Route,Long>, JpaSpecificationExecutor<Order> {
}
