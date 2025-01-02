package com.example.cosmocats.service;

import com.example.cosmocats.domain.Product;
import com.example.cosmocats.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productEntityRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp() {

        product = Product.builder()
                .id(1L)
                .name("Test Product")
                .description("Test Description")
                .price(BigDecimal.valueOf(100.0))
                .build();
    }

    @Test
    public void testCreateProduct() {
        when(productEntityRepository.create(any(Product.class))).thenReturn(product);

        var createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals(product.getName(), createdProduct.getName());
        assertEquals(product.getPrice(), createdProduct.getPrice());
        verify(productEntityRepository, times(1)).create(any(Product.class));
    }

    @Test
    public void testFindById() {
        when(productEntityRepository.findById(1L)).thenReturn(Optional.of(product));

        var foundProductDTO = productService.findById(1L);

        assertNotNull(foundProductDTO);
        assertThat(foundProductDTO).isPresent();
        assertEquals("Test Product", foundProductDTO.get().getName());
        assertEquals(BigDecimal.valueOf(100.0), foundProductDTO.get().getPrice());
    }

    @Test
    public void testFindByIdNotFound() {
        when(productEntityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.findById(1L));
    }

    @Test
    public void testUpdateProduct() {
        when(productEntityRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productEntityRepository.update(anyLong(), any(Product.class))).thenReturn(product);

        var updatedProduct = new Product("Updated Product", BigDecimal.valueOf(150.0));

        var resultingProduct = productService.updateProduct(1L, updatedProduct);

        assertNotNull(resultingProduct);
        assertEquals(updatedProduct.getName(), resultingProduct.getName());
        assertEquals(updatedProduct.getPrice(), resultingProduct.getPrice());
    }

    @Test
    public void testUpdateProductNotFound() {
        when(productEntityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.updateProduct(1L, product));
    }

    @Test
    public void testDeleteProduct() {
        when(productEntityRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productEntityRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteProductNotFound() {
        when(productEntityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.deleteProduct(1L));
    }
}
