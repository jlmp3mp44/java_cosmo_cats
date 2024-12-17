package com.example.cosmocats.service;

import com.example.cosmocats.featuretoggle.exception.FeatureNotAvailableException;
import com.example.cosmocats.model.Product;
import com.example.cosmocats.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setPrice(BigDecimal.valueOf(99.99));
        testProduct.setStockQuantity(10);
    }

    @Test
    void getAllProducts_WhenFeatureEnabled_ShouldReturnProducts() {
        // Arrange
        List<Product> expectedProducts = Arrays.asList(testProduct);
        when(productRepository.findAll()).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = productService.getAllProducts();

        // Assert
        assertEquals(expectedProducts, actualProducts);
        verify(productRepository).findAll();
    }

    @Test
    void getProduct_WhenFeatureEnabled_ShouldReturnProduct() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        // Act
        Optional<Product> result = productService.getProduct(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(testProduct, result.get());
    }

    @Test
    void createProduct_WhenFeatureEnabled_ShouldCreateProduct() {
        // Arrange
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        // Act
        Product created = productService.createProduct(testProduct);

        // Assert
        assertNotNull(created);
        assertEquals(testProduct.getName(), created.getName());
        verify(productRepository).save(testProduct);
    }
}
