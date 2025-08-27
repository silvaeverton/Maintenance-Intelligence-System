package com.example.maintenance_Intelligence_system.mappers;

import com.example.maintenance_Intelligence_system.dtos.ResponseTechnicalReportDto;
import com.example.maintenance_Intelligence_system.dtos.ResumeReportTechnicalDto;
import com.example.maintenance_Intelligence_system.models.TechnicalReport;
import org.springframework.data.domain.Page;

import java.util.List;

public class TechnicianReportMapper {

    public static ResponseTechnicalReportDto toDto(TechnicalReport report) {

        if(report == null) return null;

        ResponseTechnicalReportDto dto = new ResponseTechnicalReportDto();
        dto.setId(report.getId());
        dto.setOperator(report.getOperator());
        dto.setObservation(report.getObservation());
        dto.setStatusOrder(report.getStatusOrder());
        dto.setProblemFound(report.getProblemFound());
        dto.setSolutionAdopted(report.getSolutionAdopted());
        dto.setStartTime(report.getStartTime());
        dto.setCloseTime(report.getCloseTime());
        dto.setProblemCategory(report.getProblemCategory());
        dto.setRequestCategoryOS(report.getRequestCategoryOS());
        dto.setTypeOfStop(report.getTypeOfStop());

        if(report.getMachine() != null) {
            dto.setMachineId(report.getMachine().getId());
        }

        if(report.getTechnicianResponsible() != null) {
            dto.setTechnicianId(report.getTechnicianResponsible().getId());
        }

        return dto;

    }

    public static ResumeReportTechnicalDto toResumeDto(TechnicalReport report) {
        if(report == null) return null;

        ResumeReportTechnicalDto dto = new ResumeReportTechnicalDto();
        dto.setId(report.getId());
        dto.setTechnicianId(report.getTechnicianResponsible().getId());
        dto.setMachineId(report.getMachine().getId());
        dto.setProblemFound(report.getProblemFound());
        dto.setDateProblem(report.getAcceptedCalled());

      return dto;
    }

    public static Page<ResumeReportTechnicalDto> toResumePage(Page<TechnicalReport> reports){
        return reports
                .map(TechnicianReportMapper::toResumeDto);

    }

    public static List<ResponseTechnicalReportDto> toDtoList(List<TechnicalReport> reports) {

        return reports.stream()
                .map(TechnicianReportMapper::toDto)
                .toList();
    }
}
