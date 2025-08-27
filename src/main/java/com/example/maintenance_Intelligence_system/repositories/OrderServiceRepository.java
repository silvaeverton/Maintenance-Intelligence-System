package com.example.maintenance_Intelligence_system.repositories;

import com.example.maintenance_Intelligence_system.enums.PriorityCategory;
import com.example.maintenance_Intelligence_system.models.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {
    List<OrderService> findByTechnicianResponsible_Id(Long technicianId);
    List<OrderService> findByPriorityCategory(PriorityCategory priorityCategory);

    @Query("SELECT o FROM OrderService o WHERE o.dateRequisition BETWEEN :start AND :end")
    List<OrderService> findOrderByDateBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
