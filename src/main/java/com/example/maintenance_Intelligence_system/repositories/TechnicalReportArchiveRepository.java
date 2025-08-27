package com.example.maintenance_Intelligence_system.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalReportArchiveRepository extends JpaRepository<Long,TechnicalReportArchive> {
}
