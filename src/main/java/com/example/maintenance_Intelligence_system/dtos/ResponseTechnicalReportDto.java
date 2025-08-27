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
    private Long id;
    private String operator;
    private String observation;
    private String problemFound;
    private String solutionAdopted;
    private StatusOrder statusOrder;
    private LocalDateTime startTime;
    private LocalDateTime closeTime;
    private ProblemCategory problemCategory;
    private RequestCategoryOS requestCategoryOS;
    private String typeOfStop;
    private Long machineId;
    private Long technicianId;
}
