package com.example.maintenance_Intelligence_system.models;

import com.example.maintenance_Intelligence_system.enums.PriorityCategory;
import com.example.maintenance_Intelligence_system.enums.ProblemCategory;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import jakarta.persistence.*;
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

    String operator;
    String observation;
    String problemFound;
    String solutionAdopted;
    StatusOrder statusOrder;
    LocalDateTime startTime;
    LocalDateTime closeTime;
    LocalDateTime acceptedCalled;
    ProblemCategory problemCategory;
    PriorityCategory priorityCategory;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    Machine machine;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    Technician technicianResponsible;


}
