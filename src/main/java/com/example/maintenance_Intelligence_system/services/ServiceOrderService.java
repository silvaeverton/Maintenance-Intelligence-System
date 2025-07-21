package com.example.maintenance_Intelligence_system.services;

import com.example.maintenance_Intelligence_system.dtos.RequestOrderServiceDto;
import com.example.maintenance_Intelligence_system.dtos.ResponseOrderServiceDto;
import com.example.maintenance_Intelligence_system.enums.PriorityCategory;
import com.example.maintenance_Intelligence_system.models.OrderService;

import java.time.LocalDate;
import java.util.List;

public interface ServiceOrderService {
    public OrderService createOderService(RequestOrderServiceDto dto);
    public ResponseOrderServiceDto findOrderServiceById(Long idOrder);
    public List<ResponseOrderServiceDto> allOrder();
    public List<ResponseOrderServiceDto> findOrderByPriority(PriorityCategory priorityCategory);
    public List<ResponseOrderServiceDto> findOrderByDateBetween(LocalDate start,LocalDate end);
    public OrderService updateOrderService(Long idOrder, RequestOrderServiceDto dto);
    public void canceledOrderService(Long idOrder);
    public void definePriorityOrder(Long idOrder);
    public void executedOrderService(Long idOrder);


}
