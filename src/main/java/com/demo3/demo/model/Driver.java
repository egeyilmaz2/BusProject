package com.demo3.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter

@Entity
@Table(name="Driver")
public class Driver implements Serializable {
    @Id
    @SequenceGenerator(name="driver_seq",allocationSize = 1)
    @GeneratedValue(generator = "driver_seq", strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToMany(mappedBy = "driver")
    Set<BusAndDriver> busAndDrivers;

    @Column(columnDefinition = "name")
    private String name;


}
