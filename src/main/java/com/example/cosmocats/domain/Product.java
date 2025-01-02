package com.example.cosmocats.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

import lombok.*;
@Getter
@Setter
@Builder(toBuilder = true)
public class Product {

    Long id;
    String name;
    String description;
    BigDecimal price;
    Category category;
}

