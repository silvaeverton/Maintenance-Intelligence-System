package com.example.maintenance_Intelligence_system.models;

import com.example.maintenance_Intelligence_system.enums.ProblemCategory;
import com.example.maintenance_Intelligence_system.enums.RequestCategoryOS;
import com.example.maintenance_Intelligence_system.enums.StatusMachine;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "technical_report_archive")
public class TechnicalReportArchive {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String operator;
    private String observation;
    private String problemFound;
    private String solutionAdopted;
    private StatusOrder statusOrder;
    private StatusMachine statusMachine;
    private String typeOfStop;
    private LocalDateTime startTime;
    private LocalDateTime closeTime;
    private RequestCategoryOS requestCategoryOS;
    private LocalDateTime acceptedCalled;
    private ProblemCategory problemCategory;


    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technicianResponsible;

    public TechnicalReportArchive() {}

    public TechnicalReportArchive(TechnicalReport report) {
        this.operator = report.getOperator();
        this.observation = report.getObservation();
        this.problemFound = report.getProblemFound();
        this.solutionAdopted = report.getSolutionAdopted();
        this.statusOrder = report.getStatusOrder();
        this.typeOfStop = report.getTypeOfStop();
        this.startTime = report.getStartTime();
        this.closeTime = report.getCloseTime();
        this.requestCategoryOS = report.getRequestCategoryOS();
        this.acceptedCalled = report.getAcceptedCalled();
        this.problemCategory = report.getProblemCategory();
        this.machine = report.getMachine();
        this.technicianResponsible = report.getTechnicianResponsible();

    }
}
