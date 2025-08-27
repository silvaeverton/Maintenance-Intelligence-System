package com.example.maintenance_Intelligence_system.services.serviceImpl;

import com.example.maintenance_Intelligence_system.dtos.RequestESP32Dto;
import com.example.maintenance_Intelligence_system.dtos.RequestTechnicalReportDto;
import com.example.maintenance_Intelligence_system.dtos.ResponseTechnicalReportDto;
import com.example.maintenance_Intelligence_system.enums.StatusMachine;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import com.example.maintenance_Intelligence_system.exceptions.custom.BadRequestException;
import com.example.maintenance_Intelligence_system.exceptions.custom.NotFoundException;
import com.example.maintenance_Intelligence_system.mappers.TechnicianReportMapper;
import com.example.maintenance_Intelligence_system.models.Machine;
import com.example.maintenance_Intelligence_system.models.TechnicalReport;
import com.example.maintenance_Intelligence_system.models.Technician;
import com.example.maintenance_Intelligence_system.repositories.MachineRepository;
import com.example.maintenance_Intelligence_system.repositories.TechnicianReportRepository;
import com.example.maintenance_Intelligence_system.services.MachineService;
import com.example.maintenance_Intelligence_system.services.TechnicalReportService;
import com.example.maintenance_Intelligence_system.services.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceTechnicianReportService implements TechnicalReportService {

    private final TechnicianReportRepository technicianReportRepository;

    private final TechnicianService technicianService;

    private final MachineRepository machineRepository;

    private final MachineService machineService;


    @Override
    public TechnicalReport openTechnicalReport(RequestESP32Dto requestESP32Dto) {

        Machine machine = machineService.searchMachineById(requestESP32Dto.getMachineId());
        TechnicalReport report = new TechnicalReport();
        report.setMachine(machine);
        report.setStartTime(LocalDateTime.now());
        report.setStatusOrder(StatusOrder.OPEN);
        report.setStatusMachine(requestESP32Dto.getStatusMachine());

        return technicianReportRepository.saveAndFlush(report);
    }

    @Override
    public TechnicalReport toAlterTechnicalReport(RequestESP32Dto requestESP32Dto) {
        List<TechnicalReport> report = technicianReportRepository.findByMachineId(requestESP32Dto.getMachineId());

        Machine machine = machineService.searchMachineById(requestESP32Dto.getMachineId());

        Optional<TechnicalReport> reportOptional = report.stream()
                .filter(r -> r.getStatusOrder().equals(StatusOrder.IN_PROGRESS))
                .findFirst();

        if(reportOptional.isEmpty()) {
            throw new NotFoundException("The report not found for Machine ID:" + machine.getId(),404 );
        }

        TechnicalReport technicalReport = reportOptional.get();
        technicalReport.setCloseTime(LocalDateTime.now());
        technicalReport.setStatusOrder(StatusOrder.FINISH);
        technicalReport.setStatusMachine(StatusMachine.IN_MAINTENANCE_MACHINE_OPERATED);


        machineRepository.saveAndFlush(machine);
        return technicianReportRepository.saveAndFlush(technicalReport);

    }

    @Override
    public TechnicalReport updateTechnicalReport(RequestTechnicalReportDto dto) {
        TechnicalReport reportExist = searchRtById(dto.getId());
        Technician technician = technicianService.searchTechnicianById(dto.getTechnicianId());

        if(reportExist.getStatusOrder().equals(StatusOrder.CLOSED)) {
            throw new BadRequestException("The Report is Closed",400);
        }
        if(dto.getTechnicianId() != null) {reportExist.setTechnicianResponsible(technician);}
        if(dto.getOperator() != null) {reportExist.setOperator(dto.getOperator());}
        if(dto.getStatusOrder() != null) {reportExist.setStatusOrder(dto.getStatusOrder());}
        if(dto.getObservation() != null) {reportExist.setObservation(dto.getObservation());}
        if(dto.getProblemCategory() != null) {reportExist.setProblemCategory(dto.getProblemCategory());}
        if(dto.getStatusOrder() != null) {reportExist.setStatusOrder(dto.getStatusOrder());}
        if(dto.getProblemFound() != null) {reportExist.setProblemFound(dto.getProblemFound());}
        if(dto.getSolutionAdopted() != null) {reportExist.setSolutionAdopted(dto.getSolutionAdopted());}

        return technicianReportRepository.saveAndFlush(reportExist);

    }

    @Override
    public ResponseTechnicalReportDto findRtById(Long idReport) {
        TechnicalReport report = searchRtById(idReport);

     return TechnicianReportMapper.toDto(report);

    }

    @Override
    public List<ResponseTechnicalReportDto> allReposts() {
        List<TechnicalReport>  reports = technicianReportRepository.findAll();

        if(reports.isEmpty()) {
            throw new NotFoundException("Report List Not Found",404);
        }

        return TechnicianReportMapper.toDtoList(reports);
    }

    @Override
    public List<ResponseTechnicalReportDto> reportsByMachine(Long idMachine) {
        List<TechnicalReport> reports = technicianReportRepository.findByMachineId(idMachine);

        if(reports.isEmpty()) {
            throw new NotFoundException("There are no reports for this machine",404);
        }
        return TechnicianReportMapper.toDtoList(reports);

    }

    @Override
    public List<ResponseTechnicalReportDto> reportsByTechnician(Long idTech) {
        List<TechnicalReport> reports = technicianReportRepository.findByTechnicianId(idTech);

        if(reports.isEmpty()) {
            throw new NotFoundException("There are no reports for this technician",404);
        }

        return TechnicianReportMapper.toDtoList(reports);
    }

    @Override
    public List<ResponseTechnicalReportDto> findByDateBetween(LocalDate start, LocalDate end) {
        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(LocalTime.MAX);

        List<TechnicalReport> reports = technicianReportRepository.findByDateBetween(startDateTime,endDateTime);

        if(reports.isEmpty()) {
            throw new NotFoundException("There is no Report for the Selected date",404);
        }
        return TechnicianReportMapper.toDtoList(reports);
    }

    @Override
    public void toCloseReport(Long idReport) {
        TechnicalReport report = searchRtById(idReport);

        if(report.getStatusOrder().equals(StatusOrder.CLOSED)) {
            throw new BadRequestException("The Report is Closed",400);
        }
        report.setStatusOrder(StatusOrder.CLOSED);

        technicianReportRepository.saveAndFlush(report);

    }

    @Override
    public void toAcceptedReport(Long idReport) {
        TechnicalReport technicalReport = searchRtById(idReport);

        if(technicalReport.getStatusOrder().equals(StatusOrder.OPEN)) {

            technicalReport.setStatusOrder(StatusOrder.IN_PROGRESS);
            technicalReport.setAcceptedCalled(LocalDateTime.now());

            technicianReportRepository.saveAndFlush(technicalReport);

        }else {
            throw new BadRequestException("The report has already been accepted",400);
        }

    }

    public TechnicalReport searchRtById(Long idReport) {
        return technicianReportRepository.findById(idReport).orElseThrow(()-> new NotFoundException("Report not Found",404));
    }
}
