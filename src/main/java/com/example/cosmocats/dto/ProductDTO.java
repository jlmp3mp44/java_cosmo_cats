package com.example.cosmocats.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDTO(
        @NotNull(message = "Product ID cannot be null")
        Long id,
        @NotNull(message = "Product name cannot be null")
        String name,
        @NotNull(message = "Product price cannot be null")
        BigDecimal price,
        Long categoryId
) {
}
