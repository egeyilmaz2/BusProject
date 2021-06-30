package com.demo3.demo.model;

import com.demo3.demo.dto.BusDto;
import com.demo3.demo.repository.BusRepository;
import lombok.AllArgsConstructor;
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
@Table(name="Driver")
public class Driver implements Serializable {
    @Id
    @SequenceGenerator(name="driver_seq",allocationSize = 1)
    @GeneratedValue(generator = "driver_seq", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "driver")
    Set<BusAndDriver> busAndDrivers;
}
