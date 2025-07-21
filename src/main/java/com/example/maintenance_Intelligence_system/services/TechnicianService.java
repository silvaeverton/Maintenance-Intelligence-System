package com.example.maintenance_Intelligence_system.services;

import com.example.maintenance_Intelligence_system.dtos.RequestTechnicianDto;
import com.example.maintenance_Intelligence_system.dtos.ResponseTechnicianDto;
import com.example.maintenance_Intelligence_system.models.Technician;

import java.util.List;

public interface TechnicianService {
    public Technician createTechnician(RequestTechnicianDto dto);
    public ResponseTechnicianDto findByTechnician(Long idTech);
    public List<ResponseTechnicianDto> allTechnician();
    public Technician updateTechnician(Long idTech, RequestTechnicianDto dto);
    public  void inactiveTechnician(Long tech);
}
