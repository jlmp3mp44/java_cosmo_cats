package com.example.cosmocats.repository;

import com.example.cosmocats.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    @Override
    public Product create(Product product) {
        product.setId(nextId++);
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product update(Long id, Product product) {
        product.setId(id);
        products.put(id, product);
        return product;
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(products.values());
    }

    @Override
    public boolean existsById(Long id) {
        return products.containsKey(id);
    }
}
