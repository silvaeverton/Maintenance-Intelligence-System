package com.example.maintenance_Intelligence_system.services.serviceImpl;

import com.example.maintenance_Intelligence_system.dtos.RequestTechnicianDto;
import com.example.maintenance_Intelligence_system.dtos.ResponseTechnicianDto;
import com.example.maintenance_Intelligence_system.enums.GenericState;
import com.example.maintenance_Intelligence_system.exceptions.custom.NotFoundException;
import com.example.maintenance_Intelligence_system.models.Technician;
import com.example.maintenance_Intelligence_system.repositories.TechnicianRepository;
import com.example.maintenance_Intelligence_system.services.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceTechnicianService implements TechnicianService {

    private final TechnicianRepository technicianRepository;

    @Override
    public Technician createTechnician(RequestTechnicianDto dto) {

        Technician technician = new Technician();
        technician.setName(dto.getName());
        technician.setRegistration(dto.getRegistration());

        return technicianRepository.saveAndFlush(technician);
    }

    @Override
    public ResponseTechnicianDto findByTechnician(Long idTech) {
        Technician technician = searchTechnicianById(idTech);

        ResponseTechnicianDto dto = new ResponseTechnicianDto();
        dto.setId(technician.getId());
        dto.setName(technician.getName());
        dto.setGenericState(technician.getGenericState());
        dto.setRegistration(technician.getRegistration());


        return dto;
    }

    @Override
    public List<ResponseTechnicianDto> allTechnician() {
        List<Technician> technicianList = technicianRepository.findAll();

        if(technicianList.isEmpty()) {
            throw new NotFoundException("Technician List is Empty",404);
        }

        return technicianList.stream().map(technician -> {
            ResponseTechnicianDto dto = new ResponseTechnicianDto();
            dto.setId(technician.getId());
            dto.setName(technician.getName());
            dto.setGenericState(technician.getGenericState());
            dto.setRegistration(technician.getRegistration());
            return dto;
        }).toList();

    }

    @Override
    public Technician updateTechnician(Long idTech, RequestTechnicianDto dto) {
        Technician technician = searchTechnicianById(idTech);

        if(dto.getName()!= null) { technician.setName(dto.getName());}
        if(dto.getRegistration()!= null) {technician.setRegistration(dto.getRegistration());}

        return technicianRepository.saveAndFlush(technician);
    }

    @Override
    public void  alterStateTechnician (Long idTech, GenericState status) {
        Technician technician = searchTechnicianById(idTech);

        technician.setGenericState(status);
        technicianRepository.saveAndFlush(technician);

    }

    @Override
    public Technician searchTechnicianById(Long idTech) {

        return technicianRepository.findById(idTech).orElseThrow(()-> new NotFoundException("Technician Not Found", 404));
    }
}
