package com.example.cosmocats.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  @NotNull(message = "Product ID cannot be null")
  private Long id;
  @NotNull(message = "Product name cannot be null")
  private String name;
  @NotNull(message = "Product price cannot be null")
  private BigDecimal price;
  private Long categoryId; 
}
