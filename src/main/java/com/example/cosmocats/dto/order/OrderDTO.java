package com.example.cosmocats.dto.order;

import java.math.BigDecimal;
import java.util.List;

public record OrderDTO(
  Long id,
  List<Long> productIds,
  BigDecimal totalPrice,
  String status
) {

}
