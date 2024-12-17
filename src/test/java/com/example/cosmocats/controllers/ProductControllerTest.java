package com.example.cosmocats.controllers;

import com.example.cosmocats.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final ProductDTO VALID_PRODUCT_DTO = ProductDTO.builder()
        .name("Cosmic Cat Food")
        .description("Space-grade nutrition for your feline friend")
        .price(BigDecimal.valueOf(29.99))
        .categoryId(1L)
        .build();

    private static final ProductDTO INVALID_PRODUCT_DTO = ProductDTO.builder()
        .name("Regular Name") // Валідне ім'я, але інші обов'язкові поля відсутні
        .description("Test Description")
        .price(BigDecimal.valueOf(-10.00)) // Негативна ціна
        .build();

    @Test
    @SneakyThrows
    void getAllProducts_Success() throws Exception{
        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void getProductById_ValidId_Success() throws Exception {
        mockMvc.perform(get("/api/products/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @SneakyThrows
    void getProductById_InvalidId_NotFound() throws Exception {
        mockMvc.perform(get("/api/products/99"))
            .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void createProduct_ValidData_Success() throws Exception{
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRODUCT_DTO)))
            .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    void createProduct_InvalidData_BadRequest() throws Exception {
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRODUCT_DTO)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void updateProduct_ValidData_Success() throws Exception{
        mockMvc.perform(put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRODUCT_DTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @SneakyThrows
    void updateProduct_InvalidId_NotFound() throws Exception{
        mockMvc.perform(put("/api/products/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRODUCT_DTO)))
            .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void deleteProduct_ValidId_Success() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    void deleteProduct_InvalidId_NotFound() throws Exception{
        mockMvc.perform(delete("/api/products/99"))
            .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void createProduct_Conflict() throws Exception{
        mockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(VALID_PRODUCT_DTO)));

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRODUCT_DTO)))
            .andExpect(status().isConflict());
    }
}


