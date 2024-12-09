package com.example.cosmocats.controllers;

import com.example.cosmocats.dto.ProductDTO;
import com.example.cosmocats.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping
  public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
    ProductDTO createdProduct = productService.createProduct(productDTO);
    return ResponseEntity.status(201).body(createdProduct);
  }

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    List<ProductDTO> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
    ProductDTO product = productService.getProductById(id);
    return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
    ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
    return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    boolean isDeleted = productService.deleteProduct(id);
    return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
