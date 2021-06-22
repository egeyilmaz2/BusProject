package com.demo3.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name="Route")
public class Route implements Serializable {
    @Id
    @SequenceGenerator(name="route_seq",allocationSize = 1)
    @GeneratedValue(generator = "route_seq", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(columnDefinition = "name")
    private String name;

    @OneToMany(mappedBy = "route")
    Set<RouteAndStop>  routeAndStops;
    @OneToMany(mappedBy = "route")
    Set<BusAndRoute> busAndRoutes;

}
