package com.example.cosmocats.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@Builder
@Getter
@Setter
@AllArgsConstructor
public class CategoryDTO {
  private Long id;
  private String name;
}
