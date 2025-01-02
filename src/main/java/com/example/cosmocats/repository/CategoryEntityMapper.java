package com.example.cosmocats.repository;

import com.example.cosmocats.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
interface CategoryEntityMapper {

    @ToEntity
    CategoryEntity toEntity(Category category);

    @ToDomain
    Category toDomain(CategoryEntity entity);

    @Qualifier
    @Target(ElementType. METHOD)
    @Retention(RetentionPolicy. CLASS)
    @interface ToDomain {}

    @Qualifier
    @Target(ElementType. METHOD)
    @Retention(RetentionPolicy. CLASS)
    @interface ToEntity {}

}
