package com.portale.pizzeria.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.portale.pizzeria.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {

    @NotEmpty
    @Valid
    private final List<Pizza> pizze;

    private OrderStatus status = OrderStatus.RICEVUTO;

    @JsonCreator
    public Order(@JsonProperty("pizze") List<Pizza> pizze) {
        this.pizze = pizze;
    }
}

