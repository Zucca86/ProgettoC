package com.portale.pizzeria.service;

import com.portale.pizzeria.OrderStatus;
import com.portale.pizzeria.dto.Order;

public interface OrderService {

    Long save(Order order);

    void setStatus(Long id, OrderStatus status);

    Order findOrder(Long id);
}
