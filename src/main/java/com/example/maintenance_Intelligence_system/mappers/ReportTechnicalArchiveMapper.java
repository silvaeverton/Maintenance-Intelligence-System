package com.example.maintenance_Intelligence_system.mappers;

import com.example.maintenance_Intelligence_system.dtos.ResponseTechnicalReportDto;
import com.example.maintenance_Intelligence_system.dtos.ResumeReportTechnicalDto;
import com.example.maintenance_Intelligence_system.models.TechnicalReportArchive;

import java.util.List;

public class ReportTechnicalArchiveMapper {

    public static ResponseTechnicalReportDto toDto(TechnicalReportArchive report) {

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



    public static List<ResponseTechnicalReportDto> toDtoList(List<TechnicalReportArchive> reports) {

        return reports.stream()
                .map(ReportTechnicalArchiveMapper::toDto)
                .toList();
    }
}
