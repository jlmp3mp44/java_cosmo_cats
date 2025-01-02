package com.example.cosmocats.service;

import com.example.cosmocats.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

     Product createProduct(Product product);
     List<Product> getAllProducts();
     Optional<Product> findById(Long id);
     Product updateProduct(Long id, Product product) ;
     void deleteProduct(Long id);
}
