package com.example.cosmocats.mapper;

import com.example.cosmocats.domain.Product;
import com.example.cosmocats.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);
}


