package com.example.maintenance_Intelligence_system.mappers;

import com.example.maintenance_Intelligence_system.dtos.ResponseOrderServiceDto;
import com.example.maintenance_Intelligence_system.models.OrderService;

import java.util.List;

public class OrderServiceMapper {

    public static ResponseOrderServiceDto toDto(OrderService order) {

        if(order == null) return null;

        ResponseOrderServiceDto dto = new ResponseOrderServiceDto();
        dto.setId(order.getId());
        dto.setRequester(order.getRequester());
        dto.setSector(order.getSector());
        dto.setServiceToPerformed(order.getServiceToPerformed());
        dto.setCategoryOS(order.getCategoryOS());
        dto.setPriorityCategory(order.getPriorityCategory());
        dto.setStatusOrder(order.getStatusOrder());
        dto.setProblem(order.getProblem());
        dto.setObservation(order.getObservation());
        dto.setDateRequisition(order.getDateRequisition());
        dto.setExpectedDate(order.getExpectedDate());
        dto.setCompletionDate(order.getCompletionDate());

        return dto;
    }

    public static List<ResponseOrderServiceDto> toDtoList(List<OrderService> orderServices) {

      return orderServices.stream()
                .map(OrderServiceMapper::toDto)
                .toList();
    }
}
