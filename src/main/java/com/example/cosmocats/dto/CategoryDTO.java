package com.example.cosmocats.dto;

import lombok.Builder;

@Builder
public record CategoryDTO(
  Long id,
  String name
) {
}
