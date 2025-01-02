package com.example.cosmocats.domain;

import lombok.*;


@Builder(toBuilder = true)
@Value
public class Category {
    Long id;
    String name;
}
