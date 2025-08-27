package com.example.maintenance_Intelligence_system.dtos;

import com.example.maintenance_Intelligence_system.enums.ProblemCategory;
import com.example.maintenance_Intelligence_system.enums.StatusMachine;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RequestTechnicalReportDto {

    private String operator;
    private String observation;
    private String problemFound;
    private String solutionAdopted;
    private StatusOrder statusOrder;
    private Long technicianId;
    private StatusMachine statusMachine;

    @Enumerated(EnumType.STRING)
    private  ProblemCategory problemCategory;
}
