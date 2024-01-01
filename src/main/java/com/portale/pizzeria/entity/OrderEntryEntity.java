package com.portale.pizzeria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "ordineentry")
@Data
public class OrderEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "orderEntry")
    private PizzaEntity pizza;

    @ManyToOne
    private OrderEntity order;

    @ManyToMany
    @JoinTable(
            name="ingredientDaAggiungere",
            joinColumns = @JoinColumn(name= "id_ordineentry"),
            inverseJoinColumns = @JoinColumn(name = "id_ingrediente")
    )
    private Set<IngredienteEntity> ingredientiDaAggiungere  ;

    @ManyToMany
    @JoinTable(
            name="ingredientDaRimuovere",
            joinColumns = @JoinColumn(name= "id_ordineentry"),
            inverseJoinColumns = @JoinColumn(name = "id_ingrediente")
    )
    private Set<IngredienteEntity> ingredientiDaRimuovere;
}
