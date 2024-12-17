package com.example.cosmocats.repository;

import com.example.cosmocats.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByAvailable(boolean available);
    List<Product> findByPriceLessThan(BigDecimal price);
    List<Product> findByStockQuantityGreaterThan(Integer quantity);
}