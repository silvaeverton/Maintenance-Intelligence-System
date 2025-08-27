package com.example.maintenance_Intelligence_system.services;

import com.example.maintenance_Intelligence_system.dtos.RequestOrderServiceDto;
import com.example.maintenance_Intelligence_system.dtos.RequestOsDtoExe;
import com.example.maintenance_Intelligence_system.dtos.ResponseOrderServiceDto;
import com.example.maintenance_Intelligence_system.enums.PriorityCategory;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import com.example.maintenance_Intelligence_system.models.OrderService;

import java.time.LocalDate;
import java.util.List;

public interface ServiceOrderService {
    public OrderService createOderService(RequestOrderServiceDto dto);
    public ResponseOrderServiceDto findOrderServiceById(Long idOrder);
    public List<ResponseOrderServiceDto> allOrder();
    public List<ResponseOrderServiceDto> findOrderByPriority(PriorityCategory priorityCategory);
    public List<ResponseOrderServiceDto> findOrderByDateBetween(LocalDate start,LocalDate end);
    public List<ResponseOrderServiceDto> findOrderByTechnician(Long idTechnician);
    public OrderService updateOrderService(Long idOrder, RequestOsDtoExe dto);
    public void definePriorityOrder(Long idOrder, PriorityCategory priorityCategory);
    public void alterStatusOrderService(Long idOrder, StatusOrder statusOrder);
    public OrderService searchOrderById(Long idOrder);


}
