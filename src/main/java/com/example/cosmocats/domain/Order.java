package com.example.cosmocats.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;


@Builder(toBuilder = true)
@Value
public class Order {
    Long id;
    List<Product> products;
    BigDecimal totalPrice;
    String status;
}
