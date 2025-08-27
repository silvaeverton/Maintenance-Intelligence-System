package com.example.maintenance_Intelligence_system.dtos;

import com.example.maintenance_Intelligence_system.enums.PriorityCategory;
import com.example.maintenance_Intelligence_system.enums.RequestCategoryOS;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RequestOsDtoExe {
    private String serviceToPerformed;
    private RequestCategoryOS categoryOS;
    private PriorityCategory priorityCategory;
    private StatusOrder statusOrder;
    private String problem;
    private  String observation;
    private LocalDate expectedDate;
    private Long technicianId;


}
