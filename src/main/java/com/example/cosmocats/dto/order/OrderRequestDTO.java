package com.example.cosmocats.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class OrderRequestDTO {
  @NotNull(message = "Entries cannot be null")
  List<OrderDTO> entries;

  @NotNull(message = "Total price cannot be null")
  @Min(value = 0)
  Double totalPrice;
}
