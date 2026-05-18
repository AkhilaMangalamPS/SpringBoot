package org.example.phase3.service;

import jakarta.transaction.Transactional;
import org.example.phase3.dto.OrderDto;
import org.example.phase3.entity.Order;
import org.example.phase3.entity.User;
import org.example.phase3.exception.ResourceNotFoundException;
import org.example.phase3.repository.OrderRepository;
import org.example.phase3.repository.UserRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;


import org.example.phase3.dto.OrderDto;
@Service
public class OrderServiceImplementation implements OrderService{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImplementation(OrderRepository orderRepository, UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Order order = new Order();
        order.setProduct(dto.product());
        order.setUser(user);
        Order savedOrder = orderRepository.save(order);
        return new OrderDto(savedOrder.getId(), savedOrder.getProduct(),savedOrder.getUser().getId());
    }
}
