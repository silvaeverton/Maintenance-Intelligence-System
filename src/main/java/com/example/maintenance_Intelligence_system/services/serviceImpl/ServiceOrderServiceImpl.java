package com.example.maintenance_Intelligence_system.services.serviceImpl;

import com.example.maintenance_Intelligence_system.dtos.RequestOrderServiceDto;
import com.example.maintenance_Intelligence_system.dtos.RequestOsDtoExe;
import com.example.maintenance_Intelligence_system.dtos.ResponseOrderServiceDto;
import com.example.maintenance_Intelligence_system.enums.PriorityCategory;
import com.example.maintenance_Intelligence_system.enums.StatusOrder;
import com.example.maintenance_Intelligence_system.exceptions.custom.BadRequestException;
import com.example.maintenance_Intelligence_system.exceptions.custom.NotFoundException;
import com.example.maintenance_Intelligence_system.mappers.OrderServiceMapper;
import com.example.maintenance_Intelligence_system.models.OrderService;
import com.example.maintenance_Intelligence_system.models.Technician;
import com.example.maintenance_Intelligence_system.repositories.OrderServiceRepository;
import com.example.maintenance_Intelligence_system.services.ServiceOrderService;
import com.example.maintenance_Intelligence_system.services.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceOrderServiceImpl implements ServiceOrderService {

    private final OrderServiceRepository orderServiceRepository;

    private final TechnicianService technicianService;

    @Override
    public OrderService createOderService(RequestOrderServiceDto dto) {
        OrderService orderService = new OrderService();

        orderService.setRequester(dto.getRequester());
        orderService.setSector(dto.getSector());
        orderService.setLieder(dto.getLieder());
        orderService.setServiceToPerformed(dto.getServiceToPerformed());
        orderService.setObservation(dto.getObservation());
        orderService.setStatusOrder(StatusOrder.OPEN);
        orderService.setDateRequisition(LocalDate.now());

        return orderServiceRepository.saveAndFlush(orderService);
    }

    @Override
    public ResponseOrderServiceDto findOrderServiceById(Long idOrder) {
        OrderService orderService = searchOrderById(idOrder);

        return OrderServiceMapper.toDto(orderService);
    }

    @Override
    public List<ResponseOrderServiceDto> allOrder() {
        List<OrderService> services = orderServiceRepository.findAll();

        if(services.isEmpty()) {
            throw new NotFoundException("The Order Service List is Empty",404);
        }

        return OrderServiceMapper.toDtoList(services);
    }

    @Override
    public List<ResponseOrderServiceDto> findOrderByPriority(PriorityCategory priorityCategory) {
        List<OrderService> orderServices = orderServiceRepository.findByPriorityCategory(priorityCategory);

        if(orderServices.isEmpty()) {
            throw new NotFoundException("There is no Order with this priority",404);
        }

        return OrderServiceMapper.toDtoList(orderServices);
    }

    @Override
    public List<ResponseOrderServiceDto> findOrderByDateBetween(LocalDate start, LocalDate end) {

        LocalDateTime startLocalDateTime = start.atStartOfDay();
        LocalDateTime endLocalDateTime = end.atTime(LocalTime.MAX);

        List<OrderService> orderServices = orderServiceRepository.findOrderByDateBetween(startLocalDateTime,endLocalDateTime);

        if(orderServices.isEmpty()) {
            throw new NotFoundException("There is no Order in this period",404);
        }
        return OrderServiceMapper.toDtoList(orderServices);
    }

    @Override
    public List<ResponseOrderServiceDto> findOrderByTechnician(Long idTechnician) {

        List<OrderService> orderServices = orderServiceRepository.findByTechnicianResponsible_Id(idTechnician);

        if(orderServices.isEmpty()) {
            throw new NotFoundException("There is no Order in this Technician",404);
        }

        return OrderServiceMapper.toDtoList(orderServices);
    }

    @Override
    public OrderService updateOrderService(Long idOrder, RequestOsDtoExe dto) {
        OrderService order = searchOrderById(idOrder);
        Technician technician = technicianService.searchTechnicianById(dto.getTechnicianId());

        if(dto.getStatusOrder() != null) {order.setStatusOrder(dto.getStatusOrder());}
        if(dto.getProblem() != null) {order.setProblem(dto.getProblem());}
        if(dto.getObservation() != null) {order.setObservation(dto.getObservation());}
        if(dto.getServiceToPerformed() != null) {order.setServiceToPerformed(dto.getServiceToPerformed());}
        if(dto.getCategoryOS() != null) {order.setCategoryOS(dto.getCategoryOS());}
        if(dto.getExpectedDate() != null) {order.setExpectedDate(dto.getExpectedDate());}
        if(dto.getPriorityCategory() != null) {order.setPriorityCategory(dto.getPriorityCategory());}
        if(dto.getTechnicianId() != null) { order.setTechnicianResponsible(technician);}

        return orderServiceRepository.saveAndFlush(order);
    }

    @Override
    public void definePriorityOrder(Long idOrder, PriorityCategory priorityCategory) {
        OrderService orderService = searchOrderById(idOrder);

        orderService.setPriorityCategory(priorityCategory);

        orderServiceRepository.saveAndFlush(orderService);

    }

    @Override
    public void alterStatusOrderService(Long idOrder, StatusOrder statusOrder) {
        OrderService orderService = searchOrderById(idOrder);

        if(orderService.getStatusOrder() != StatusOrder.CLOSED) {
            orderService.setStatusOrder(statusOrder);
        }
        if(statusOrder.equals(StatusOrder.CLOSED) && orderService.getStatusOrder() != StatusOrder.CLOSED) {
            orderService.setStatusOrder(StatusOrder.CLOSED);
            orderService.setCompletionDate(LocalDate.now());
        } else {
            throw new BadRequestException("The Order is Closed!",400);
        }
        orderServiceRepository.saveAndFlush(orderService);
    }

    @Override
    public OrderService searchOrderById(Long idOrder) {
        return orderServiceRepository.findById(idOrder)
                .orElseThrow(()-> new NotFoundException("Order Service Not Found",404));
    }
}
