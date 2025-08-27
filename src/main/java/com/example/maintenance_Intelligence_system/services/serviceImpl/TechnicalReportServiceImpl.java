package com.example.maintenance_Intelligence_system.services.serviceImpl;

import com.example.maintenance_Intelligence_system.dtos.*;
import com.example.maintenance_Intelligence_system.enums.GenericState;
import com.example.maintenance_Intelligence_system.enums.RequestCategoryOS;
import com.example.maintenance_Intelligence_system.enums.StatusMachine;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import com.example.maintenance_Intelligence_system.exceptions.custom.BadRequestException;
import com.example.maintenance_Intelligence_system.exceptions.custom.NotFoundException;
import com.example.maintenance_Intelligence_system.mappers.ReportTechnicalArchiveMapper;
import com.example.maintenance_Intelligence_system.mappers.TechnicianReportMapper;
import com.example.maintenance_Intelligence_system.models.Machine;
import com.example.maintenance_Intelligence_system.models.TechnicalReport;
import com.example.maintenance_Intelligence_system.models.TechnicalReportArchive;
import com.example.maintenance_Intelligence_system.models.Technician;
import com.example.maintenance_Intelligence_system.repositories.MachineRepository;
import com.example.maintenance_Intelligence_system.repositories.TechnicalReportArchiveRepository;
import com.example.maintenance_Intelligence_system.repositories.TechnicianReportRepository;
import com.example.maintenance_Intelligence_system.services.MachineService;
import com.example.maintenance_Intelligence_system.services.TechnicalReportService;
import com.example.maintenance_Intelligence_system.services.TechnicianService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TechnicalReportServiceImpl implements TechnicalReportService {

    private final TechnicalReportArchiveRepository technicalReportArchiveRepository;

    private final TechnicianReportRepository technicianReportRepository;

    private final TechnicianService technicianService;

    private final MachineRepository machineRepository;

    private final MachineService machineService;


    @Override
    public TechnicalReport openTechnicalReport(RequestESP32Dto requestESP32Dto) {

        Machine machine = machineService.searchMachineById(requestESP32Dto.getMachineId());

        if(machine.getGenericState().equals(GenericState.ACTIVE)) {
            TechnicalReport report = new TechnicalReport();
            report.setMachine(machine);
            report.setStartTime(LocalDateTime.now());
            report.setStatusOrder(StatusOrder.OPEN);
            report.setStatusMachine(requestESP32Dto.getStatusMachine());
            report.setTypeOfStop(report.getStatusMachine().toString());

            return technicianReportRepository.saveAndFlush(report);
        } else {
            throw  new BadRequestException("Unable to create report! Machine inactive", 400);
        }


    }

    @Override
    public TechnicalReport toAlterTechnicalReport(AlterESP32Dto alterESP32Dto) {
        List<TechnicalReport> report = technicianReportRepository.findByMachine_Id(alterESP32Dto.getMachineId());

        Machine machine = machineService.searchMachineById(alterESP32Dto.getMachineId());

        Optional<TechnicalReport> reportOptional = report.stream()
                .filter(r -> r.getStatusOrder().equals(StatusOrder.IN_PROGRESS))
                .findFirst();

        if(reportOptional.isEmpty()) {
            throw new NotFoundException("The report not found for Machine ID:" + machine.getId(),404 );
        }

        TechnicalReport technicalReport = reportOptional.get();
        technicalReport.setCloseTime(LocalDateTime.now());
        technicalReport.setStatusOrder(StatusOrder.FINISH);
        technicalReport.setStatusMachine(StatusMachine.MACHINE_OPERATED);


        machineRepository.saveAndFlush(machine);
        return technicianReportRepository.saveAndFlush(technicalReport);

    }

    @Override
    public TechnicalReport updateTechnicalReport(Long idReport,RequestTechnicalReportDto dto) {
        TechnicalReport reportExist = searchRtById(idReport);
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
    public Page<ResumeReportTechnicalDto> allReposts(Pageable pageable) {
        Page<TechnicalReport>  reports = technicianReportRepository.findAll(pageable);

        if(reports.isEmpty()) {
            throw new NotFoundException("Report List Not Found",404);
        }

        return TechnicianReportMapper.toResumePage(reports);
    }

    @Override
    public Page<ResumeReportTechnicalDto> reportsByMachine(Long idMachine, Pageable pageable) {
        Page<TechnicalReport> reports = technicianReportRepository.findByMachine_Id(idMachine,pageable);

        if(reports.isEmpty()) {
            throw new NotFoundException("There are no reports for this machine",404);
        }
        return TechnicianReportMapper.toResumePage(reports);

    }

    @Override
    public Page<ResumeReportTechnicalDto> reportsByTechnician(Long idTech, Pageable pageable) {
        Page<TechnicalReport> reports = technicianReportRepository.findByTechnicianResponsible_Id(idTech,pageable);

        if(reports.isEmpty()) {
            throw new NotFoundException("There are no reports for this technician",404);
        }

        return TechnicianReportMapper.toResumePage(reports);
    }

    @Override
    public Page<ResumeReportTechnicalDto> findByDateBetween(LocalDate start, LocalDate end, Pageable pageable) {

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(LocalTime.MAX);

        Page<TechnicalReport> reports = technicianReportRepository.findByStartTimeBetween(startDateTime,endDateTime,pageable);

        if(reports.isEmpty()) {
            throw new NotFoundException("There is no Report for the Selected date",404);
        }
        return TechnicianReportMapper.toResumePage(reports);
    }

    @Override
    public void toCloseReport(Long idReport) {
        TechnicalReport report = searchRtById(idReport);
        Machine machine = machineService.searchMachineById(report.getMachine().getId());

        if(report.getStatusOrder().equals(StatusOrder.CLOSED)) {
            throw new BadRequestException("The Report is Closed",400);
        }
        report.setStatusOrder(StatusOrder.CLOSED);
        machine.setStatusMachine(machine.getStatusMachine());

        technicianReportRepository.saveAndFlush(report);
        machineRepository.saveAndFlush(machine);

    }

    @Override
    public void toAcceptedReport(Long idReport) {
        TechnicalReport technicalReport = searchRtById(idReport);

        if(technicalReport.getStatusOrder().equals(StatusOrder.OPEN)) {

            technicalReport.setStatusOrder(StatusOrder.IN_PROGRESS);
            technicalReport.setAcceptedCalled(LocalDateTime.now());
            technicalReport.setRequestCategoryOS(RequestCategoryOS.CORRECTIVE);

            technicianReportRepository.saveAndFlush(technicalReport);

        }else {
            throw new BadRequestException("The report has already been accepted",400);
        }

    }

    @Override
    public List<TechnicalReport> searchDateBetween(LocalDateTime start, LocalDateTime end) {

        return technicianReportRepository.findByStartTimeBetween(start,end);

    }

    @Override
    public List<TechnicalReport> findReportsOlderThanTwoYear() {

        LocalDate twoYearAgo = LocalDate.now().minusYears(2);
        return technicianReportRepository.findByStartTimeBefore(twoYearAgo);
    }

    @Override
    @Transactional
    public void archiveOldReports() {
        List<TechnicalReport> oldReports = findReportsOlderThanTwoYear();

        for(TechnicalReport report : oldReports) {
            TechnicalReportArchive archived = new TechnicalReportArchive(report);
            technicalReportArchiveRepository.save(archived);
            technicianReportRepository.delete(report);
        }
    }

    @Override
    public List<ResponseTechnicalReportDto> listArchiveReport() {
        List<TechnicalReportArchive> reportArchiveList = technicalReportArchiveRepository.findAll();


        if(reportArchiveList.isEmpty()) {
            throw new NotFoundException("The Archive Report List not found", 404);
        }
        return ReportTechnicalArchiveMapper.toDtoList(reportArchiveList);
    }

    @Scheduled(cron = "0 0 2 1 * *")
    public void scheduleArchive() {
        archiveOldReports();
    }


    public TechnicalReport searchRtById(Long idReport) {
        return technicianReportRepository.findById(idReport).orElseThrow(()-> new NotFoundException("Report not Found",404));
    }
}
