package com.example.cosmocats.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.cosmocats.TestcontainersConfiguration;
import com.example.cosmocats.model.Product;
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
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setDescription("Description 1");
        product1.setPrice(new BigDecimal("10.00"));
        product1.setStockQuantity(100);
        productService.createProduct(product1);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(new BigDecimal("20.00"));
        product2.setStockQuantity(200);
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
        Product product = products.get(0);

        Optional<Product> fetchedProduct = productService.getProduct(product.getId());
        assertTrue(fetchedProduct.isPresent());
        assertEquals(product.getName(), fetchedProduct.get().getName());
    }

    @Test
    @Rollback
    void testCreateProduct() {
        Product newProduct = new Product();
        newProduct.setName("New Product");
        newProduct.setDescription("New Description");
        newProduct.setPrice(new BigDecimal("15.00"));
        newProduct.setStockQuantity(50);

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
        Product product = products.get(0);

        boolean result = productService.deleteProduct(product.getId());
        assertTrue(result);

        List<Product> remainingProducts = productService.getAllProducts();
        assertEquals(1, remainingProducts.size());
    }

    @Test
    void testGetProductsBelowPrice() {
        List<Product> productsBelowPrice = productService.getProductsBelowPrice(new BigDecimal("15.00"));
        assertEquals(1, productsBelowPrice.size());
        assertEquals("Product 1", productsBelowPrice.get(0).getName());
    }

    @Test
    void testUpdateStock() {
        List<Product> products = productService.getAllProducts();
        Product product = products.get(0);

        Product updatedProduct = productService.updateStock(product.getId(), 500);
        assertEquals(500, updatedProduct.getStockQuantity());
    }

    @Test
    void testGetProductDetails() {
        List<Product> products = productService.getAllProducts();
        Product product = products.get(0);

        String details = productService.getProductDetails(product.getId());
        assertTrue(details.contains(product.getName()));
        assertTrue(details.contains(product.getDescription()));
        assertTrue(details.contains(product.getPrice().toString()));
        assertTrue(details.contains(String.valueOf(product.getStockQuantity())));
    }

    @Test
    void testGetServiceStatus() {
        String status = productService.getServiceStatus();
        assertEquals("Product service is operational", status);
    }
}