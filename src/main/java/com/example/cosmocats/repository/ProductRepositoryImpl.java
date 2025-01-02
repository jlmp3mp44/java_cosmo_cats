package com.example.cosmocats.repository;

import com.example.cosmocats.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class ProductRepositoryImpl implements ProductRepository {

    private final ProductEntityRepository productEntityRepository;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public Product create(Product product) {
        var entity = productEntityMapper.toEntity(product);
        var saved = productEntityRepository.save(entity);
        return productEntityMapper.toDomain(saved);
    }

    @Override
    public Product update(Long id, Product product) {
        product.setId(id);
        return create(product);
    }

    @Override
    public void deleteById(Long id) {
        productEntityRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productEntityRepository.findById(id).map(productEntityMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return productEntityRepository.findAll()
                .stream().map(productEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Product> findAllByPriceLessThen(BigDecimal threshold) {
        return productEntityRepository.findAllByPriceLessThan(threshold)
                .stream().map(productEntityMapper::toDomain).toList();
    }

    @Override
    public boolean existsById(Long id) {
        return productEntityRepository.existsById(id);
    }
}
