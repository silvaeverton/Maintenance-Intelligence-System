package com.example.maintenance_Intelligence_system.models;

import com.example.maintenance_Intelligence_system.enums.StatusMachine;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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


}
