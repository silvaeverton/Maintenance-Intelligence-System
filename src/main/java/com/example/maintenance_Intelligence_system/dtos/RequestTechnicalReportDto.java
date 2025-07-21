package com.example.maintenance_Intelligence_system.dtos;

import com.example.maintenance_Intelligence_system.enums.ProblemCategory;
import com.example.maintenance_Intelligence_system.enums.RequestCategoryOS;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RequestTechnicalReportDto {
    Long id;
    String operator;
    String observation;
    String problemFound;
    String solutionAdopted;
    StatusOrder statusOrder;
    Long technicianId;

    @Enumerated(EnumType.STRING)
    RequestCategoryOS categoryOS;

    @Enumerated(EnumType.STRING)
    ProblemCategory problemCategory;
}
