package com.example.maintenance_Intelligence_system.dtos;

import com.example.maintenance_Intelligence_system.enums.GenericState;
import com.example.maintenance_Intelligence_system.enums.StatusMachine;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseMachineDto {
    private Long id;
    private String name;
    private String sector;
    private String manufacturer;
    private String model;
    private GenericState genericState;
    private StatusMachine statusMachine;
}
