package com.example.maintenance_Intelligence_system.repositories;

import com.example.maintenance_Intelligence_system.models.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

}
