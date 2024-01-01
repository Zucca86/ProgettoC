package com.portale.pizzeria.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pizza")
@Data
public class PizzaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne
    private OrderEntryEntity orderEntry;
}
