package com.example.maintenance_Intelligence_system.services.serviceImpl;

import com.example.maintenance_Intelligence_system.dtos.RequestMachineDto;
import com.example.maintenance_Intelligence_system.dtos.ResponseMachineDto;
import com.example.maintenance_Intelligence_system.enums.GenericState;
import com.example.maintenance_Intelligence_system.enums.StatusMachine;
import com.example.maintenance_Intelligence_system.exceptions.custom.NotFoundException;
import com.example.maintenance_Intelligence_system.models.Machine;
import com.example.maintenance_Intelligence_system.repositories.MachineRepository;
import com.example.maintenance_Intelligence_system.services.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;

    @Override
    public Machine createMachine(RequestMachineDto requestMachineDto) {
        Machine machine = new Machine();
        machine.setName(requestMachineDto.getName());
        machine.setGenericState(GenericState.ACTIVE);
        machine.setModel(requestMachineDto.getModel());
        machine.setSector(requestMachineDto.getSector());
        machine.setManufacturer(requestMachineDto.getManufacturer());
        machine.setStatusMachine(StatusMachine.IN_MAINTENANCE_MACHINE_OPERATED);

        return machineRepository.saveAndFlush(machine);
    }

    @Override
    public ResponseMachineDto findMachineById(Long idMachine) {
        Machine machine = searchMachineById(idMachine);

        ResponseMachineDto response = new ResponseMachineDto();
        response.setId(machine.getId());
        response.setName(machine.getName());
        response.setModel(machine.getModel());
        response.setSector(machine.getSector());
        response.setGenericState(machine.getGenericState());
        response.setManufacturer(machine.getManufacturer());
        response.setStatusMachine(machine.getStatusMachine());

        return response;
    }

    @Override
    public List<ResponseMachineDto> allMachines() {
        List<Machine> machineList = machineRepository.findAll();

        if(machineList.isEmpty()) {
            throw new NotFoundException("Machine list is Empty",404);
        }
      return  machineList.stream().map(machine -> {
            ResponseMachineDto response = new ResponseMachineDto();
            response.setId(machine.getId());
            response.setName(machine.getName());
            response.setModel(machine.getModel());
            response.setSector(machine.getSector());
            response.setGenericState(machine.getGenericState());
            response.setManufacturer(machine.getManufacturer());
            response.setStatusMachine(machine.getStatusMachine());
            return  response;
        }).toList();

    }

    @Override
    public Machine updateMachine(Long idMachine, RequestMachineDto updateMachineDto) {
        Machine machine = searchMachineById(idMachine);

        if(updateMachineDto.getName() != null) {machine.setName(updateMachineDto.getName());}
        if(updateMachineDto.getModel() != null) {machine.setModel(updateMachineDto.getModel());}
        if(updateMachineDto.getSector()!= null) {machine.setSector(updateMachineDto.getSector());}
        if(updateMachineDto.getManufacturer() != null) {machine.setManufacturer(updateMachineDto.getManufacturer());}



        return machineRepository.saveAndFlush(machine);
    }

    @Override
    public void alterStateMachine(Long idMachine, GenericState status) {
        Machine machine = searchMachineById(idMachine);

        machine.setGenericState(status);

        machineRepository.saveAndFlush(machine);
    }

    public Machine searchMachineById(Long idMachine) {

        return machineRepository.findById(idMachine).orElseThrow(()-> new NotFoundException("Machine Not Found",404));
    }
}
