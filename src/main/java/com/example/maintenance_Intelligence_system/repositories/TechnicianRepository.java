package com.example.maintenance_Intelligence_system.repositories;

import com.example.maintenance_Intelligence_system.models.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {
}
