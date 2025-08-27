package com.example.maintenance_Intelligence_system.dtos;

import com.example.maintenance_Intelligence_system.enums.PriorityCategory;
import com.example.maintenance_Intelligence_system.enums.RequestCategoryOS;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ResponseOrderServiceDto {
    private Long id;
    private String requester;
    private String sector;
    private String lieder;
    private String serviceToPerformed;
    private RequestCategoryOS categoryOS;
    private PriorityCategory priorityCategory;
    private StatusOrder statusOrder;
    private String problem;
    private String observation;
    private LocalDate dateRequisition;
    private LocalDate expectedDate;
    private LocalDate completionDate;
}
