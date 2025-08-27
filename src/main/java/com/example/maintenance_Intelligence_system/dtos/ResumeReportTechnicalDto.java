package com.example.maintenance_Intelligence_system.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResumeReportTechnicalDto {

    private Long id;
    private Long machineId;
    private Long technicianId;
    private String  problemFound;
    private LocalDateTime dateProblem;

}
