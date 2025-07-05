package com.example.maintenance_Intelligence_system.dtos;

import com.example.maintenance_Intelligence_system.enums.StatusMachine;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseMachineDto {
    Long id;
    String name;
    String sector;
    String manufacturer;
    String model;
    StatusMachine statusMachine;
}
