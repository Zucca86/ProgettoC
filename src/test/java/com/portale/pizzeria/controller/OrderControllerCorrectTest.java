package com.portale.pizzeria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portale.pizzeria.OrderStatus;
import com.portale.pizzeria.WrapperTestContainer;
import com.portale.pizzeria.dto.Order;
import com.portale.pizzeria.dto.OrderCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerCorrectTest extends WrapperTestContainer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    private final String input = """
            {
                "pizze" : [
                    {
                        "id":1,
                        "ingredientiDaAggiungere": [1,2,3],
                        "ingredientiDaRimuovere": [1,2,3]
                    },
                    {
                        "id":2,
                        "ingredientiDaAggiungere": [1,2,3],
                        "ingredientiDaRimuovere": [1,2,3]
                    }        
                ]
            }
            """;

    @Test
    void posTAndUpdateAndRetrieveOrder() throws Exception {
        //Creazione dell' ordine
        final String postResult = this.mockMvc.perform(
                post("/order")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(input)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("code")))
                .andReturn().getResponse().getContentAsString();
        OrderCode orderCode = mapper.readValue(postResult, OrderCode.class);

        //controllo status dell'ordine
        final String getResult = this.mockMvc.perform(
                    get("/order/" + orderCode.getCode())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Order initialOrder = mapper.readValue(getResult, Order.class);
        Assertions.assertEquals(OrderStatus.RICEVUTO, initialOrder.getStatus());

        //aggiornamento status dell'ordine
        this.mockMvc.perform(
                patch("/order/" + orderCode.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(OrderStatus.INLAVORAZIONE.toString()))
            )
            .andDo(print())
            .andExpect(status().isOk());

        //controllo nuovo status dell'ordine
        final String newGetResult = this.mockMvc.perform(
                get("/order/" + orderCode.getCode())
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Order newStatusOrder = mapper.readValue(newGetResult, Order.class);
        Assertions.assertEquals(OrderStatus.INLAVORAZIONE, newStatusOrder.getStatus());
    }
}
