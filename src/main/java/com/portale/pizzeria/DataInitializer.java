package com.portale.pizzeria;

import com.portale.pizzeria.entity.IngredienteEntity;
import com.portale.pizzeria.entity.PizzaEntity;
import com.portale.pizzeria.repository.IngredienteRepository;
import com.portale.pizzeria.repository.PizzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private static final Logger LOG =
            LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    IngredienteRepository ingredienteRepository;

    @Override
    public void run(String...args) throws Exception {
        LOG.info("Creazione pizze");
        final PizzaEntity margherita = new PizzaEntity();
        margherita.setNome("margherita");
        final PizzaEntity diavola = new PizzaEntity();
        diavola.setNome("diavola");
        pizzaRepository.saveAll(List.of(margherita, diavola));


        LOG.info("Creazione ingredienti");
        IngredienteEntity pomodoro = new IngredienteEntity();
        pomodoro.setNome("pomodoro");
        IngredienteEntity origano = new IngredienteEntity();
        origano.setNome("origano");
        IngredienteEntity mozzarella = new IngredienteEntity();
        mozzarella.setNome("mozzarella");
        ingredienteRepository.saveAll(List.of(pomodoro, origano, mozzarella));
    }
}