package com.example.maintenance_Intelligence_system.models;

import com.example.maintenance_Intelligence_system.enums.GenericState;
import com.example.maintenance_Intelligence_system.enums.PriorityCategory;
import com.example.maintenance_Intelligence_system.enums.RequestCategoryOS;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
public class OrderService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String requester;
    private String sector;
    private String lieder;
    private String serviceToPerformed;

    @Enumerated(EnumType.STRING)
    private RequestCategoryOS categoryOS;

    @Enumerated(EnumType.STRING)
    private PriorityCategory priorityCategory;

    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    private String problem;
    private String observation;
    private LocalDate dateRequisition;
    private LocalDate expectedDate;

    @Enumerated(EnumType.STRING)
    private GenericState genericState;

    private LocalDate completionDate;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technicianResponsible;

}
