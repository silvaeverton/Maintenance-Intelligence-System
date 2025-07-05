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
    String serviceToPerformed;
    RequestCategoryOS categoryOS;
    PriorityCategory priorityCategory;
    StatusOrder statusOrder;
    String problem;
    String observation;
    LocalDate expectedDate;
    Long technicianId;


}
