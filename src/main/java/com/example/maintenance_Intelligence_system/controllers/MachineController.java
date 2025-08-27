package com.example.maintenance_Intelligence_system.controllers;

import com.example.maintenance_Intelligence_system.dtos.RequestMachineDto;
import com.example.maintenance_Intelligence_system.dtos.ResponseMachineDto;
import com.example.maintenance_Intelligence_system.enums.GenericState;
import com.example.maintenance_Intelligence_system.models.Machine;
import com.example.maintenance_Intelligence_system.services.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/machine")
public class MachineController {

    private final MachineService machineService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Machine createMachine(@RequestBody RequestMachineDto requestMachineDto) {
        return machineService.createMachine(requestMachineDto);
    }

    @GetMapping("/{idMachine}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMachineDto findMachineById(@PathVariable Long idMachine) {
        return machineService.findMachineById(idMachine);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseMachineDto> allMachines() {
        return machineService.allMachines();
    }

    @PutMapping("/{idMachine}")
    @ResponseStatus(HttpStatus.OK)
    public Machine updateMachine(@PathVariable Long idMachine,
                                 @RequestBody RequestMachineDto updateMachineDto) {
        return machineService.updateMachine(idMachine, updateMachineDto);
    }

    @PatchMapping("/{idMachine}/status")
    @ResponseStatus(HttpStatus.OK)
    public void alterStateMachine(@PathVariable Long idMachine,@RequestParam GenericState status) {
        machineService.alterStateMachine(idMachine, status);
    }
}
