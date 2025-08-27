package com.example.maintenance_Intelligence_system.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestOrderServiceDto {
    private String requester;
    private String sector;
    private String lieder;
    private String serviceToPerformed;
    private String observation;


}
