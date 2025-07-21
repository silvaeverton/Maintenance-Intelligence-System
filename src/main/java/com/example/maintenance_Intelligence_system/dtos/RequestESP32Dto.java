package com.example.maintenance_Intelligence_system.dtos;

import com.example.maintenance_Intelligence_system.enums.StatusMachine;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestESP32Dto {

   private Long machineId;

   @Enumerated(EnumType.STRING)
   private StatusMachine statusMachine;

}
