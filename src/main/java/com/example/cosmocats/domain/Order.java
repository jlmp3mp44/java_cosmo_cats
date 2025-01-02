package com.example.cosmocats.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;


@Builder(toBuilder = true)
@Value
public class Order {
    Long id;
    List<Product> products;
    BigDecimal totalPrice;
    String status;
}
