package com.portale.pizzeria.controller;

import com.portale.pizzeria.dto.OrderCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerWrongTest {

    @Autowired
    private MockMvc mockMvc;

    private final String input = """
            {
                "pizze" : []
            }
            """;

    @Test
    void emptyPizze() throws Exception {
        //Creazione dell' ordine
        this.mockMvc.perform(
                post("/order")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(input)
                )
                .andDo(print())
                .andExpect(status().is(400));
    }
}
