package com.example.cosmocats.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    @NonNull
    @NotBlank
    private String name;
    private String description;
    @NonNull
    @NotNull
    @PositiveOrZero
    private BigDecimal price;
    private Category category;
}

