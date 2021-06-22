package com.demo3.demo.model;

import com.demo3.demo.dto.BusDto;
import com.demo3.demo.repository.BusRepository;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name="Bus")
public class Bus implements Serializable {
    @Id
    @SequenceGenerator(name="bus_seq",allocationSize = 1)
    @GeneratedValue(generator = "bus_seq", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(length = 4,name = "no" )
    private String no;

    @Column(name="status")
    private boolean status=false;

    @OneToMany(mappedBy = "bus")
    Set<BusAndDriver> busAndDrivers;

    @OneToMany(mappedBy = "bus")
    Set<BusAndRoute> busAndRoutes;


   /* @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Route_id")
    private Route route;*/




}
