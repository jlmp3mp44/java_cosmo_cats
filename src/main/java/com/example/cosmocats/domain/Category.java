package com.example.cosmocats.domain;

import jakarta.persistence.*;
import lombok.*;


@Builder(toBuilder = true)
@Value
public class Category {
    Long id;
    String name;
}
