package com.example.maintenance_Intelligence_system.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String registration;

    @OneToMany(mappedBy = "technician")
    @JsonIgnore
    List<TechnicalReport> reportList = new ArrayList<>();

    @OneToMany(mappedBy = "technician")
    @JsonIgnore
    List<OrderService> orderServices = new ArrayList<>();

}
