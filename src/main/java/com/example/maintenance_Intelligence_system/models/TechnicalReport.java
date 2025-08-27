package com.example.maintenance_Intelligence_system.models;

import com.example.maintenance_Intelligence_system.enums.ProblemCategory;
import com.example.maintenance_Intelligence_system.enums.RequestCategoryOS;
import com.example.maintenance_Intelligence_system.enums.StatusMachine;
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
    private Long id;
    private String operator;
    private String observation;
    private String problemFound;
    private String solutionAdopted;

    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    @Enumerated(EnumType.STRING)
    private StatusMachine statusMachine;
    private String typeOfStop;
    private LocalDateTime startTime;
    private LocalDateTime closeTime;

    @Enumerated(EnumType.STRING)
    private RequestCategoryOS requestCategoryOS;
    private LocalDateTime acceptedCalled;

    @Enumerated(EnumType.STRING)
    private ProblemCategory problemCategory;


    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technicianResponsible;


}
