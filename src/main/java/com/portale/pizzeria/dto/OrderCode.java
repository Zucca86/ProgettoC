package com.portale.pizzeria.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCode {

    private final Long code;

    @JsonCreator
    public OrderCode(@JsonProperty("code") Long code) {
        this.code = code;
    }
}
