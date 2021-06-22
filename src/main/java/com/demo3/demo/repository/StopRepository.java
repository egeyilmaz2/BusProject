package com.demo3.demo.repository;

import com.demo3.demo.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Order;

@Repository("StopRepository")
public interface StopRepository extends JpaRepository<Stop,Long>, JpaSpecificationExecutor<Order> {
}
