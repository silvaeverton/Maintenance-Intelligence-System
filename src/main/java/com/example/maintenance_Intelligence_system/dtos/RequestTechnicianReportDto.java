package com.example.maintenance_Intelligence_system.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestTechnicianReportDto {
    String operator;
    String observation;
    String problemFound;
    String solutionAdopted;
    Long technicianResponsibleId;
}
