package com.example.cosmocats.dto.order;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {
  private Long id;
  private List<Long> productIds;
  private BigDecimal totalPrice;
  private String status;
}
