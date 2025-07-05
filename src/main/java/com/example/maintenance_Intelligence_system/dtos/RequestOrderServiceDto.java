package com.example.maintenance_Intelligence_system.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestOrderServiceDto {
    String requester;
    String sector;
    String lieder;
    String serviceToPerformed;
    String observation;


}
