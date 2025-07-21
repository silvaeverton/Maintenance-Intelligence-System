package com.example.maintenance_Intelligence_system.services;

import com.example.maintenance_Intelligence_system.dtos.RequestESP32Dto;
import com.example.maintenance_Intelligence_system.dtos.RequestTechnicalReportDto;
import com.example.maintenance_Intelligence_system.dtos.ResponseTechnicalReportDto;
import com.example.maintenance_Intelligence_system.models.TechnicalReport;

import java.time.LocalDate;
import java.util.List;

public interface TechnicalReportService {
    public TechnicalReport openTechnicalReport(RequestESP32Dto requestESP32Dto);
    public TechnicalReport toAlterTechnicalReport(RequestESP32Dto requestESP32Dto);
    public TechnicalReport updateTechnicalReport(RequestTechnicalReportDto dto);
    public ResponseTechnicalReportDto findRtById(Long idReport);
    public List<ResponseTechnicalReportDto> allReposts();
    public List<ResponseTechnicalReportDto> reportsByMachine(Long idMachine);
    public List<ResponseTechnicalReportDto> reportsByTechnician(Long idTech);
    public List<ResponseTechnicalReportDto> findByDateBetween(LocalDate start, LocalDate end);
    public void toCloseReport(Long idReport);
    public void toAcceptedReport(Long idReport);




}
