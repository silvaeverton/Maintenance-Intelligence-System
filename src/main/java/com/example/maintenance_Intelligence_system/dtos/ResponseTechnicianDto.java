package com.example.maintenance_Intelligence_system.dtos;

import com.example.maintenance_Intelligence_system.enums.GenericState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseTechnicianDto {
    private  Long id;
    private String name;
    private String registration;
    private GenericState genericState;
}
