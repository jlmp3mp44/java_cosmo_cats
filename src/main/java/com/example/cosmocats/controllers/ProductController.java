package com.example.cosmocats.controllers;

import com.example.cosmocats.dto.ProductDTO;
import com.example.cosmocats.mapper.ProductMapper;
import com.example.cosmocats.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final ProductMapper productMapper;

  @PostMapping
  public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
    var createdProduct = productService.createProduct(productMapper.toEntity(productDTO));
    return ResponseEntity.status(HttpStatus.CREATED.value())
            .body(productMapper.toDTO(createdProduct));
  }

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    var products = productService.getAllProducts();
    return ResponseEntity.ok(products.stream().map(productMapper::toDTO).toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
    var product = productService.findById(id);
    return product.map(productMapper::toDTO)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
    var updatedProduct = productService.updateProduct(id, productMapper.toEntity(productDTO));
    return ResponseEntity.ok(productMapper.toDTO(updatedProduct));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
