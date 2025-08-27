package com.example.maintenance_Intelligence_system.controllers;

import com.example.maintenance_Intelligence_system.dtos.RequestTechnicianDto;
import com.example.maintenance_Intelligence_system.dtos.ResponseTechnicianDto;
import com.example.maintenance_Intelligence_system.enums.GenericState;
import com.example.maintenance_Intelligence_system.models.Technician;
import com.example.maintenance_Intelligence_system.services.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/technician")
public class TechnicianController {

    private final TechnicianService technicianService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Technician createTechnician(@RequestBody RequestTechnicianDto dto) {
        return technicianService.createTechnician(dto);
    }

    @GetMapping("/{idTech}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTechnicianDto findByTechnician(@PathVariable Long idTech) {
        return technicianService.findByTechnician(idTech);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseTechnicianDto> allTechnician() {
        return technicianService.allTechnician();
    }

    @PutMapping("/{idTech}")
    @ResponseStatus(HttpStatus.OK)
    public Technician updateTechnician(@PathVariable Long idTech,@RequestBody RequestTechnicianDto dto) {
        return technicianService.updateTechnician(idTech, dto);
    }

    @PatchMapping("/{idTech}")
    @ResponseStatus(HttpStatus.OK)
    public void alterStateTechnician(@PathVariable Long idTech,@RequestParam GenericState status) {
        technicianService.alterStateTechnician(idTech, status);
    }
}
