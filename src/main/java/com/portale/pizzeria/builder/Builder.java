package com.portale.pizzeria.builder;

import com.portale.pizzeria.dto.Pizza;
import com.portale.pizzeria.entity.IngredienteEntity;
import com.portale.pizzeria.entity.OrderEntity;
import com.portale.pizzeria.entity.OrderEntryEntity;
import com.portale.pizzeria.entity.PizzaEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Builder {

    public static List<Pizza> toPizza(OrderEntity orderEntity) {
        return orderEntity.getOrderEntry().stream()
            .map(oe -> {
                final Set<Long> ingredientiDaAggiungere = oe.getIngredientiDaAggiungere().stream().map(IngredienteEntity::getId).collect(Collectors.toSet());
                final Set<Long> ingredientiDaRimuovere = oe.getIngredientiDaRimuovere().stream().map(IngredienteEntity::getId).collect(Collectors.toSet());
                return new Pizza(oe.getId(), ingredientiDaAggiungere, ingredientiDaRimuovere);
            })
            .collect(Collectors.toList());
    }

    public static Set<IngredienteEntity> toIngredienti(Set<Long> ingredienti) {
        return ingredienti.stream()
                .map(i -> {
                    final IngredienteEntity ingredienteEntity = new IngredienteEntity();
                    ingredienteEntity.setId(i);

                    return ingredienteEntity;
                })
                .collect(Collectors.toSet());
    }

    public static List<OrderEntryEntity> toOrderEntries(OrderEntity order, List<Pizza> pizze) {
        return pizze.stream()
                .map(p -> {
                    final OrderEntryEntity orderEntry = new OrderEntryEntity();
                    orderEntry.setPizza(toPizza(p.getId()));
                    orderEntry.setIngredientiDaAggiungere(toIngredienti(p.getIngredientiDaAggiungere()));
                    orderEntry.setIngredientiDaRimuovere(toIngredienti(p.getIngredientiDaRimuovere()));
                    orderEntry.setOrder(order);

                    return orderEntry;
                })
                .collect(Collectors.toList());
    }

    public static PizzaEntity toPizza(Long pizzaId) {
        final PizzaEntity pizza = new PizzaEntity();
        pizza.setId(pizzaId);

        return pizza;
    }
}
