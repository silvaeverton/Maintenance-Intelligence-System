package com.example.maintenance_Intelligence_system.controllers;

import com.example.maintenance_Intelligence_system.dtos.RequestOrderServiceDto;
import com.example.maintenance_Intelligence_system.dtos.RequestOsDtoExe;
import com.example.maintenance_Intelligence_system.dtos.ResponseOrderServiceDto;
import com.example.maintenance_Intelligence_system.enums.PriorityCategory;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import com.example.maintenance_Intelligence_system.models.OrderService;
import com.example.maintenance_Intelligence_system.services.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderService")
public class OrderServiceController {

    private final ServiceOrderService serviceOrderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderService createOderService(@RequestBody RequestOrderServiceDto dto) {
        return serviceOrderService.createOderService(dto);
    }

    @GetMapping("/{idOrder}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseOrderServiceDto findOrderServiceById(@PathVariable Long idOrder) {
        return serviceOrderService.findOrderServiceById(idOrder);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseOrderServiceDto> allOrder() {
        return serviceOrderService.allOrder();
    }

    @GetMapping("/priority")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseOrderServiceDto> findOrderByPriority(@RequestParam PriorityCategory priorityCategory) {
        return serviceOrderService.findOrderByPriority(priorityCategory);
    }

    @GetMapping("by-date")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseOrderServiceDto> findOrderByDateBetween(@RequestParam("start")
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                                @RequestParam("end")
                                                                    @DateTimeFormat(iso =DateTimeFormat.ISO.DATE) LocalDate end) {
        return serviceOrderService.findOrderByDateBetween(start, end);
    }

    @PutMapping("/{idOrder}")
    @ResponseStatus(HttpStatus.OK)
    public OrderService updateOrderService(@PathVariable Long idOrder,@RequestBody RequestOsDtoExe dto) {
        return serviceOrderService.updateOrderService(idOrder, dto);
    }

    @PatchMapping("/{idOrder}/priority")
    @ResponseStatus(HttpStatus.OK)
    public void definePriorityOrder(@PathVariable Long idOrder,@RequestParam PriorityCategory priorityCategory) {
        serviceOrderService.definePriorityOrder(idOrder, priorityCategory);
    }

    @GetMapping("/{idTechnician}")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseOrderServiceDto> findOrderByTechnician(@PathVariable Long idTechnician) {
        return serviceOrderService.findOrderByTechnician(idTechnician);
    }

    @PatchMapping("/{idOrder}/status")
    @ResponseStatus(HttpStatus.OK)
    public void alterStatusOrderService(@PathVariable Long idOrder,@RequestParam StatusOrder statusOrder) {
        serviceOrderService.alterStatusOrderService(idOrder, statusOrder);
    }

}
