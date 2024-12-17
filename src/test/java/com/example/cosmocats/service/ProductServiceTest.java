package com.example.cosmocats.service;

import com.example.cosmocats.domain.Product;
import com.example.cosmocats.dto.ProductDTO;
import com.example.cosmocats.mapper.ProductMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Cosmic Cat Food");
        product.setDescription("Space-grade nutrition for your feline friend");
        product.setPrice(new BigDecimal("29.99"));

        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Cosmic Cat Food");
        productDTO.setDescription("Space-grade nutrition for your feline friend");
        productDTO.setPrice(new BigDecimal("29.99"));
    }

    @Test
    void createProduct_Success() {
        when(productMapper.toEntity(any(ProductDTO.class))).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        ProductDTO result = productService.createProduct(productDTO);

        assertNotNull(result);
        assertEquals(productDTO.getName(), result.getName());
        assertEquals(productDTO.getDescription(), result.getDescription());
        assertEquals(productDTO.getPrice(), result.getPrice());

        verify(productMapper).toEntity(productDTO);
        verify(productRepository).save(product);
        verify(productMapper).toDTO(product);
    }

    @Test
    void getAllProducts_Success() {
        List<Product> products = Arrays.asList(product);
        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        List<ProductDTO> result = productService.getAllProducts();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(productDTO.getName(), result.get(0).getName());

        verify(productRepository).findAll();
        verify(productMapper, times(1)).toDTO(any(Product.class));
    }

    @Test
    void getProductById_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        ProductDTO result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(productDTO.getName(), result.getName());
        assertEquals(productDTO.getDescription(), result.getDescription());

        verify(productRepository).findById(1L);
        verify(productMapper).toDTO(product);
    }

    @Test
    void getProductById_NotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        ProductDTO result = productService.getProductById(99L);

        assertNull(result);
        verify(productRepository).findById(99L);
        verify(productMapper, never()).toDTO(any());
    }

    @Test
    void updateProduct_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        ProductDTO updatedDTO = new ProductDTO();
        updatedDTO.setName("Updated Name");
        updatedDTO.setDescription("Updated Description");
        updatedDTO.setPrice(new BigDecimal("39.99"));

        ProductDTO result = productService.updateProduct(1L, updatedDTO);

        assertNotNull(result);
        verify(productRepository).findById(1L);
        verify(productRepository).save(product);
        verify(productMapper).toDTO(product);
    }

    @Test
    void updateProduct_NotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        ProductDTO result = productService.updateProduct(99L, productDTO);

        assertNull(result);
        verify(productRepository).findById(99L);
        verify(productRepository, never()).save(any());
        verify(productMapper, never()).toDTO(any());
    }

    @Test
    void deleteProduct_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        boolean result = productService.deleteProduct(1L);

        assertTrue(result);
        verify(productRepository).findById(1L);
        verify(productRepository).delete(product);
    }

    @Test
    void deleteProduct_NotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        boolean result = productService.deleteProduct(99L);

        assertFalse(result);
        verify(productRepository).findById(99L);
        verify(productRepository, never()).delete(any());
    }
}
