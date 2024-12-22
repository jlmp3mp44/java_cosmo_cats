package com.example.cosmocats.service;

import com.example.cosmocats.featuretoggle.annotation.FeatureToggle;
import com.example.cosmocats.model.Product;
import com.example.cosmocats.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Basic product operations - controlled by feature.products.enabled
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        product.setId(id);
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }

    // Detailed product operations - controlled by feature.productDetails.enabled
    public List<Product> getProductsBelowPrice(BigDecimal price) {
        return productRepository.findByPriceLessThan(price);
    }

    public Product updateStock(Long id, int quantity) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.setStockQuantity(quantity);
        return productRepository.save(product);
    }

    public String getProductDetails(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return String.format("Product: %s, Description: %s, Price: %s, Stock: %d",
            product.getName(), product.getDescription(),
            product.getPrice(), product.getStockQuantity());
    }

    // This method is always available (no feature toggle)
    public String getServiceStatus() {
        return "Product service is operational";
    }

    public Optional<Product> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product;
    }
}
