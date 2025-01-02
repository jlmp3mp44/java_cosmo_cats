package com.example.cosmocats.repository;

import com.example.cosmocats.domain.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product create(Product product);

    Product update(Long id, Product product);

    void deleteById(Long id);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findAllByPriceLessThen(BigDecimal threshold);

    boolean existsById(Long id);

}
