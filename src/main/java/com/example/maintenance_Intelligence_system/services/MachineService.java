package com.example.maintenance_Intelligence_system.services;

import com.example.maintenance_Intelligence_system.dtos.RequestMachineDto;
import com.example.maintenance_Intelligence_system.dtos.ResponseMachineDto;
import com.example.maintenance_Intelligence_system.models.Machine;

import java.util.List;

public interface MachineService {
    public Machine createMachine(RequestMachineDto requestMachineDto);
    public ResponseMachineDto findMachineById(Long idMachine);
    public List<ResponseMachineDto> allMachines();
    public Machine updateMachine(Long idMachine,RequestMachineDto updateMachineDto);
    public Boolean statusOperationMachine(Long idMachine);
    public void inactiveMachine (Long idMachine);

}
