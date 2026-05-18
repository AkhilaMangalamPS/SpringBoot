package org.example.phase3.controller;

import jakarta.validation.Valid;
import org.example.phase3.dto.OrderDto;
import org.example.phase3.repository.OrderRepository;
import org.example.phase3.service.OrderService;
import org.example.phase3.service.OrderServiceImplementation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody @Valid OrderDto dto){
        return orderService.createOrder(dto);
    }


}
