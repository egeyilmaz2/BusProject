package com.demo3.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name="Stop")
public class Stop implements Serializable {
    @Id
    @SequenceGenerator(name="stop_seq",allocationSize = 1)
    @GeneratedValue(generator = "stop_seq", strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(length = 3,name="stopNo")
    private String stopNo;

    @OneToMany(mappedBy = "stop")
    Set<RouteAndStop>  routeAndStops;
}
