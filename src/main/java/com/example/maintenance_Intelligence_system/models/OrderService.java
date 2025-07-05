package com.example.maintenance_Intelligence_system.models;

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
    Long id;
    String requester;
    String sector;
    String lieder;
    String serviceToPerformed;
    RequestCategoryOS categoryOS;
    PriorityCategory priorityCategory;
    StatusOrder statusOrder;
    String problem;
    String observation;
    LocalDate dateRequisition;
    LocalDate expectedDate;
    LocalDate completionDate;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    Technician technicianResponsible;

}
