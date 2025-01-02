package com.example.cosmocats.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class ProductEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @NonNull
    private String name;

    private String description;

    @PositiveOrZero
    @NotNull
    @NonNull
    private BigDecimal price;

    @ManyToOne
    private CategoryEntity category;

}
