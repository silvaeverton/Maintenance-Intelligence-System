package com.example.maintenance_Intelligence_system.repositories;

import com.example.maintenance_Intelligence_system.models.TechnicalReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TechnicianReportRepository extends JpaRepository<TechnicalReport, Long> {
     List<TechnicalReport> findByMachine_Id(Long machineId);
    List<TechnicalReport> findByTechnicianResponsible_Id(Long technicianId);
    List<TechnicalReport> findByStartTimeBefore(LocalDate date);
    List<TechnicalReport> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    Page<TechnicalReport> findByStartTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
    Page<TechnicalReport> findByMachine_Id(Long idMachine, Pageable pageable);
    Page<TechnicalReport> findByTechnicianResponsible_Id(Long idTech, Pageable  pageable);

}
