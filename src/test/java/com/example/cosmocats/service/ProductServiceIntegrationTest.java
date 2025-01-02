package com.example.cosmocats.service;

import com.example.cosmocats.TestcontainersConfiguration;
import com.example.cosmocats.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Import(TestcontainersConfiguration.class)
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setup() {
        // Clear existing products
        productService.getAllProducts().forEach(product -> productService.deleteProduct(product.getId()));

        // Add sample products
        Product product1 = new Product("Product 1", BigDecimal.TEN);
        product1.setDescription("Description 1");
        productService.createProduct(product1);

        Product product2 = new Product("Product 2", BigDecimal.valueOf(20));
        product2.setDescription("Description 2");
        productService.createProduct(product2);
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    void testGetProductById() {
        List<Product> products = productService.getAllProducts();
        Product product = products.getFirst();

        Optional<Product> fetchedProduct = productService.findById(product.getId());
        assertTrue(fetchedProduct.isPresent());
        assertEquals(product.getName(), fetchedProduct.get().getName());
    }

    @Test
    @Rollback
    void testCreateProduct() {
        Product newProduct = new Product("New Product", BigDecimal.valueOf(15));
        newProduct.setDescription("New Description");

        Product createdProduct = productService.createProduct(newProduct);
        assertNotNull(createdProduct.getId());
        assertEquals("New Product", createdProduct.getName());

        List<Product> products = productService.getAllProducts();
        assertEquals(3, products.size());
    }

    @Test
    @Rollback
    void testUpdateProduct() {
        List<Product> products = productService.getAllProducts();
        Product product = products.get(0);

        product.setName("Updated Name");
        Product updatedProduct = productService.updateProduct(product.getId(), product);

        assertEquals("Updated Name", updatedProduct.getName());
    }

    @Test
    @Rollback
    void testDeleteProduct() {
        List<Product> products = productService.getAllProducts();
        Product product = products.getFirst();

        productService.deleteProduct(product.getId());

        List<Product> remainingProducts = productService.getAllProducts();
        assertEquals(1, remainingProducts.size());
    }

    @Test
    void testGetProductsBelowPrice() {
        List<Product> productsBelowPrice = productService.findProductsWithPriceBellow(new BigDecimal("15.00"));
        assertEquals(1, productsBelowPrice.size());
        assertEquals("Product 1", productsBelowPrice.get(0).getName());
    }
}