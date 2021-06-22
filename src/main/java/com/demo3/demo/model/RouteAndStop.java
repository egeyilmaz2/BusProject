package com.demo3.demo.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
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
@Table(name="RouteAndStop")
public class RouteAndStop implements Serializable {
    public RouteAndStop(Route route, Stop stop){
        this.route = route;
        this.stop = stop;
    }
    @Id
    @SequenceGenerator(name="route_stop_seq",allocationSize = 1)
    @GeneratedValue(generator = "route_stop_seq", strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    Route route;

    @ManyToOne
    @JoinColumn(name = "stop_id")
    Stop stop;

}
