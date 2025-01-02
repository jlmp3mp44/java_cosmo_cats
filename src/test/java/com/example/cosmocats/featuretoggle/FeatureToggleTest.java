package com.example.cosmocats.featuretoggle;

import com.example.cosmocats.featuretoggle.exception.FeatureNotAvailableException;
import com.example.cosmocats.featuretoggle.service.FeatureToggleService;
import com.example.cosmocats.domain.Product;
import com.example.cosmocats.service.ProductService;
import com.example.cosmocats.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeatureToggleTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private FeatureToggleService featureToggleService;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product("Test Product", BigDecimal.valueOf(99.99));
        testProduct.setId(1L);
    }

    @Test
    void whenProductsFeatureEnabled_ShouldAllowBasicOperations() {
        // Arrange
        when(featureToggleService.isProductsEnabled()).thenReturn(true);
        when(productRepository.create(any(Product.class))).thenReturn(testProduct);

        // Act & Assert
        assertDoesNotThrow(() -> productService.createProduct(testProduct));
        assertDoesNotThrow(() -> productService.getAllProducts());
    }

    @Test
    void whenProductsFeatureDisabled_ShouldThrowException() {
        // Arrange
        when(featureToggleService.isProductsEnabled()).thenReturn(false);

        // Act & Assert
        assertThrows(FeatureNotAvailableException.class, 
            () -> productService.createProduct(testProduct));
        assertThrows(FeatureNotAvailableException.class, 
            () -> productService.getAllProducts());
    }

    @Test
    void whenProductDetailsFeatureDisabled_ShouldThrowException() {
        // Arrange
        when(featureToggleService.isProductsEnabled()).thenReturn(true);
        when(featureToggleService.isProductDetailsEnabled()).thenReturn(false);

        // Act & Assert
        assertThrows(FeatureNotAvailableException.class, 
            () -> productService.findProductsWithPriceBellow(BigDecimal.TEN));
    }

    @Test
    void whenAllFeaturesEnabled_ShouldAllowAllOperations() {
        // Arrange
        when(featureToggleService.isProductsEnabled()).thenReturn(true);
        when(featureToggleService.isProductDetailsEnabled()).thenReturn(true);

        // Act & Assert
        assertDoesNotThrow(() -> productService.getAllProducts());
        assertDoesNotThrow(() -> productService.findProductsWithPriceBellow(BigDecimal.TEN));
    }
}
