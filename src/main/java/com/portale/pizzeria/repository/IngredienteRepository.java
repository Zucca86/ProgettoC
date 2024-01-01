package com.portale.pizzeria.repository;

import com.portale.pizzeria.entity.IngredienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<IngredienteEntity, Long> {
}
