package com.example.cosmocats.dto.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
public record OrderRequestDTO(
  @NotEmpty(message = "Entries cannot be empty")
  List<@Valid @NotNull OrderDTO> entries,
  @NotNull(message = "Total price cannot be null")
  @PositiveOrZero
  Double totalPrice
) {

}
