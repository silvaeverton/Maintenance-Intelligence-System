package com.example.maintenance_Intelligence_system.repositories;

import com.example.maintenance_Intelligence_system.models.TechnicalReportArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalReportArchiveRepository extends JpaRepository<TechnicalReportArchive,Long> {
}
