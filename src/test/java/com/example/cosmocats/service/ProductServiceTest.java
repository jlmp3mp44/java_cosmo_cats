package com.example.cosmocats.service;

import com.example.cosmocats.domain.Product;
import com.example.cosmocats.dto.ProductDTO;
import com.example.cosmocats.service.exception.ResourceNotFoundException;
import com.example.cosmocats.service.implementation.ProductServiceImpl;
import com.example.cosmocats.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductDTO productDTO;
    private Product product;

    @BeforeEach
    public void setUp() {

        productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setPrice(BigDecimal.valueOf(100.0));

        product = Product.builder()
                .id(1L)
                .name("Test Product")
                .description("Test Description")
                .price(BigDecimal.valueOf(100.0))
                .build();
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO createdProductDTO = productService.createProduct(productDTO);

        assertNotNull(createdProductDTO);
        assertEquals("Test Product", createdProductDTO.getName());
        assertEquals(BigDecimal.valueOf(100.0), createdProductDTO.getPrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDTO foundProductDTO = productService.getProductById(1L);

        assertNotNull(foundProductDTO);
        assertEquals("Test Product", foundProductDTO.getName());
        assertEquals(BigDecimal.valueOf(100.0), foundProductDTO.getPrice());
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(1L));
    }

    @Test
    public void testUpdateProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        productDTO.setName("Updated Product");
        productDTO.setPrice(BigDecimal.valueOf(150.0));

        ProductDTO updatedProductDTO = productService.updateProduct(1L, productDTO);

        assertNotNull(updatedProductDTO);
        assertEquals("Updated Product", updatedProductDTO.getName());
        assertEquals(BigDecimal.valueOf(150.0), updatedProductDTO.getPrice());
    }

    @Test
    public void testUpdateProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(1L, productDTO));
    }

    @Test
    public void testDeleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        boolean isDeleted = productService.deleteProduct(1L);

        assertTrue(isDeleted);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(1L));
    }
}
