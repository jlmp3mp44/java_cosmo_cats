package com.example.cosmocats.service;

import com.example.cosmocats.domain.Product;
import com.example.cosmocats.featuretoggle.annotation.FeatureToggle;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

     Product createProduct(Product productDTO);
     List<Product> getAllProducts();
     Optional<Product> findById(Long id);
     Product updateProduct(Long id, Product productDTO) ;
     void deleteProduct(Long id);

     @FeatureToggle
     List<Product> findProductsWithPriceBellow(BigDecimal bigDecimal);
}
