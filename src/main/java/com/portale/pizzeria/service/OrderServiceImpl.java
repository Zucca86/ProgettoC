package com.portale.pizzeria.service;

import com.portale.pizzeria.OrderStatus;
import com.portale.pizzeria.builder.Builder;
import com.portale.pizzeria.dto.Order;
import com.portale.pizzeria.dto.Pizza;
import com.portale.pizzeria.entity.OrderEntity;
import com.portale.pizzeria.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.orderRepository = repository;
    }

    @Override
    @Transactional
    public Long save(Order order) {
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderEntry(Builder.toOrderEntries(orderEntity, order.getPizze()));
        orderEntity.setOraOrdine(LocalDateTime.now());
        orderEntity.setStatus(OrderStatus.RICEVUTO);

        final OrderEntity orderSaved = orderRepository.save(orderEntity);

        return orderSaved.getId();
    }

    @Override
    @SneakyThrows
    @Transactional
    public void setStatus(Long id, OrderStatus status) {
        final OrderEntity order = findById(id);

        order.setStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public Order findOrder(Long id) {
        final OrderEntity orderEntity = findById(id);

        final List<Pizza> pizze = Builder.toPizza(orderEntity);
        final Order order = new Order(pizze);
        order.setStatus(orderEntity.getStatus());

        return order;
    }

    private OrderEntity findById(Long id) {
       return orderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity " + id + " not found"));
    }
}
