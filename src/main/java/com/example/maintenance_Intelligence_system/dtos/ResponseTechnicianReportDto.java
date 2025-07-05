package com.example.maintenance_Intelligence_system.dtos;

import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseTechnicianReportDto {
    Long machineId;
    String operator;
    String observation;
    String problemFound;
    String solutionAdopted;
    StatusOrder statusOrder;
    LocalDateTime startTime;
    LocalDateTime closeTime;
    Long technicianResponsibleId;
}
