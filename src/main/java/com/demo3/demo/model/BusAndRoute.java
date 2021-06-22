package com.demo3.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="BusAndRoute")
public class BusAndRoute {
    public BusAndRoute(Bus bus, Route route){
        this.bus=bus;
        this.route=route;
    }
    @Id
    @SequenceGenerator(name="bus_route_sequance",allocationSize = 1)
    @GeneratedValue(generator = "bus_route_sequance", strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    Bus bus;

    @ManyToOne
    @JoinColumn(name = "route_id")
    Route route;


}
