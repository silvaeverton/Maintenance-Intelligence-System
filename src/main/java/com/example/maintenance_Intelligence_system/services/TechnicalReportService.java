package com.example.maintenance_Intelligence_system.services;

import com.example.maintenance_Intelligence_system.dtos.*;
import com.example.maintenance_Intelligence_system.models.TechnicalReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TechnicalReportService {
    public TechnicalReport openTechnicalReport(RequestESP32Dto requestESP32Dto);
    public TechnicalReport toAlterTechnicalReport(AlterESP32Dto alterESP32Dto);
    public TechnicalReport updateTechnicalReport(Long idReport,RequestTechnicalReportDto dto);
    public ResponseTechnicalReportDto findRtById(Long idReport);
    public Page<ResumeReportTechnicalDto> allReposts(Pageable pageable);
    public Page<ResumeReportTechnicalDto> reportsByMachine(Long idMachine,Pageable pageable);
    public Page<ResumeReportTechnicalDto> reportsByTechnician(Long idTech,Pageable pageable);
    public Page<ResumeReportTechnicalDto> findByDateBetween(LocalDate start, LocalDate end, Pageable pageable);
    public void toCloseReport(Long idReport);
    public void toAcceptedReport(Long idReport);
    public List<TechnicalReport> searchDateBetween(LocalDateTime start, LocalDateTime end);
    public List<TechnicalReport> findReportsOlderThanTwoYear();
    public void archiveOldReports();
    public List<ResponseTechnicalReportDto> listArchiveReport();



}
