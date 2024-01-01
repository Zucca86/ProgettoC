package com.portale.pizzeria.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class Pizza {

    @NotNull
    private final Long id;
    private final Set<Long> ingredientiDaAggiungere;
    private final Set<Long> ingredientiDaRimuovere;
}
