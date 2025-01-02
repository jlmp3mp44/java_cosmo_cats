package com.example.cosmocats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByPriceLessThan(BigDecimal threshold);
}
