package com.example.maintenance_Intelligence_system.models;

import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
public class TechnicalReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Machine machine;
    String operator;
    String observation;
    String problemFound;
    String solutionAdopted;
    StatusOrder statusOrder;
    LocalDateTime startTime;
    LocalDateTime closeTime;
    Technician technicianResponsible;


}
