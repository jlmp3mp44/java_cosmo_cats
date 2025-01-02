package com.example.cosmocats.service;

import com.example.cosmocats.domain.Product;
import com.example.cosmocats.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(@Validated Product product) {
        return productRepository.create(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Long id, @Validated Product product) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }
        return productRepository.update(id, product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductsWithPriceBellow(BigDecimal threshold) {
        return productRepository.findAllByPriceLessThen(threshold);
    }

}
