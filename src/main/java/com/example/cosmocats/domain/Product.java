package com.example.cosmocats.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

import lombok.*;

@Data
@Builder
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    BigDecimal price;
    @ManyToOne
    Category category;
}

