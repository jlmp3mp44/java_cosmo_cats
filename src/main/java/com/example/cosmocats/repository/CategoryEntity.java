package com.example.cosmocats.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class CategoryEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @NonNull
    private String name;

}
