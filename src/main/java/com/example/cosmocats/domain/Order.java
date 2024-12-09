package com.example.cosmocats.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToMany
    List<Product> products;
    BigDecimal totalPrice;
    String status;
}
