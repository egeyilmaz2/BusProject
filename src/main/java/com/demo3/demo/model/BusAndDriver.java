package com.demo3.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="busAndDriver")
public class BusAndDriver  {
    public BusAndDriver(Bus bus,Driver driver){
        this.bus=bus;
        this.driver=driver;
    }
    @Id
    @SequenceGenerator(name="bus_driver_sequance",allocationSize = 1)
    @GeneratedValue(generator = "bus_driver_sequance", strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    Bus bus;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    Driver driver;



}
