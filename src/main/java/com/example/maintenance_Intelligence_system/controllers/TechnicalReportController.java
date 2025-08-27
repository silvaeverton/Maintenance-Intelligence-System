package com.example.maintenance_Intelligence_system.controllers;

import com.example.maintenance_Intelligence_system.dtos.*;
import com.example.maintenance_Intelligence_system.models.TechnicalReport;
import com.example.maintenance_Intelligence_system.services.TechnicalReportService;
import com.example.maintenance_Intelligence_system.services.serviceImpl.ExcelExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/technicalReport")
public class TechnicalReportController {

    private final TechnicalReportService technicalReportService;

   private final ExcelExportService excelExportService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TechnicalReport openTechnicalReport(@RequestBody RequestESP32Dto requestESP32Dto) {
        return technicalReportService.openTechnicalReport(requestESP32Dto);
    }

    @PutMapping("/ESP32")
    @ResponseStatus(HttpStatus.OK)
    public TechnicalReport toAlterTechnicalReport(@RequestBody AlterESP32Dto alterESP32Dto) {
        return technicalReportService.toAlterTechnicalReport(alterESP32Dto);
    }

    @PutMapping("/finish/{idReport}")
    @ResponseStatus(HttpStatus.OK)
    public TechnicalReport updateTechnicalReport(@PathVariable Long idReport ,@RequestBody RequestTechnicalReportDto dto) {
        return technicalReportService.updateTechnicalReport(idReport,dto);
    }

    @GetMapping("/{idReport}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTechnicalReportDto findRtById(@PathVariable Long idReport) {
        return technicalReportService.findRtById(idReport);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ResumeReportTechnicalDto> allReposts(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return technicalReportService.allReposts(pageable);
    }

    @GetMapping("/by-machine/{idMachine}")
    @ResponseStatus(HttpStatus.OK)
    public Page<ResumeReportTechnicalDto> reportsByMachine(@PathVariable Long idMachine,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page,size);
        return technicalReportService.reportsByMachine(idMachine,pageable);
    }

    @GetMapping("/by-tech/{idTech}")
    @ResponseStatus(HttpStatus.OK)
    public Page<ResumeReportTechnicalDto> reportsByTechnician(@PathVariable Long idTech,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page,size);
        return technicalReportService.reportsByTechnician(idTech,pageable);
    }

    @GetMapping("/by-date")
    @ResponseStatus(HttpStatus.OK)
    public Page<ResumeReportTechnicalDto> findByDateBetween(@RequestParam ("start")
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                              @RequestParam("end")
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page,size);
        return technicalReportService.findByDateBetween(start, end,pageable);
    }

    @PatchMapping("/{idReport}")
    @ResponseStatus(HttpStatus.OK)
    public void toCloseReport(@PathVariable Long idReport) {
        technicalReportService.toCloseReport(idReport);
    }

    @PatchMapping("/accepted/{idReport}")
    @ResponseStatus(HttpStatus.OK)
    public void toAcceptedReport(@PathVariable Long idReport) {
        technicalReportService.toAcceptedReport(idReport);
    }

    @GetMapping("/archive")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseTechnicalReportDto> listArchiveReport() {
        return technicalReportService.listArchiveReport();
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportExcel(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {


        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(LocalTime.MAX);

        List<TechnicalReport> reports = technicalReportService.searchDateBetween(startDateTime,endDateTime);
        byte[] excelFile = excelExportService.generateExcel(reports);


        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorios.xlsx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok().headers(headers).body(excelFile);
    }
}
