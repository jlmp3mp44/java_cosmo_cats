package com.example.cosmocats.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder(toBuilder = true)
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}

