package com.example.maintenance_Intelligence_system.dtos;

import com.example.maintenance_Intelligence_system.enums.ProblemCategory;
import com.example.maintenance_Intelligence_system.enums.RequestCategoryOS;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseTechnicalReportDto {
    Long machineId;
    String operator;
    String observation;
    String problemFound;
    String solutionAdopted;
    StatusOrder statusOrder;
    LocalDateTime startTime;
    LocalDateTime closeTime;
    Long technicianResponsibleId;
    RequestCategoryOS categoryOS;
    ProblemCategory problemCategory;
}
