package com.example.maintenance_Intelligence_system.models;

import com.example.maintenance_Intelligence_system.enums.StatusMachine;
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
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String sector;
    String manufacturer;
    String model;
    StatusMachine statusMachine;

    @OneToMany(mappedBy = "machine")
    @JsonIgnore
    List<TechnicalReport> reportList = new ArrayList<>();


}
