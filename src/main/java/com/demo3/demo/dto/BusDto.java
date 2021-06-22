package com.demo3.demo.dto;

import com.demo3.demo.model.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class BusDto {
    private  long id;
    private String no;
    private Route route;
    private Boolean status;
}
