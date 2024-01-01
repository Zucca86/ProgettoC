package com.portale.pizzeria.controller;

import com.portale.pizzeria.OrderStatus;
import com.portale.pizzeria.dto.Order;
import com.portale.pizzeria.dto.OrderCode;
import com.portale.pizzeria.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderCode> createOrder(@Valid @RequestBody Order ordine) {
        final Long orderId = orderService.save(ordine);

        return new ResponseEntity<OrderCode>(new OrderCode(orderId), HttpStatus.OK);
    }

    @PatchMapping("/order/{id}")
    public ResponseEntity<String> editOrderStatus(@PathVariable Long id, @RequestBody OrderStatus status) {
        orderService.setStatus(id, status);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
        return new ResponseEntity<Order>(orderService.findOrder(id), HttpStatus.OK);
    }
}
