package com.example.cosmocats.repository;

import com.example.cosmocats.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CategoryEntityMapper.class)
interface ProductEntityMapper {

    @Mapping(target = "category", source = "category", qualifiedBy = CategoryEntityMapper.ToEntity.class)
    ProductEntity toEntity(Product product);

    @Mapping(target = "category", source = "category", qualifiedBy = CategoryEntityMapper.ToDomain.class)
    Product toDomain(ProductEntity productEntity);

}
