package com.portale.pizzeria.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ingrediente")
@Data
public class IngredienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}
