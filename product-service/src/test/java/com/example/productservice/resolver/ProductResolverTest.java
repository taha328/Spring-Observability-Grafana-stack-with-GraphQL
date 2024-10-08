package com.example.productservice.resolver;

import com.example.productservice.entities.ProductEntity;
import com.example.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductResolverTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductResolver productResolver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Creating product instances with id, name, price, and stockQuantity
        ProductEntity product1 = new ProductEntity(1L, "testProduct1", 100.0, 999);
        ProductEntity product2 = new ProductEntity(2L, "testProduct2", 150.0, 500);

        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        List<ProductEntity> products = productResolver.getAllProducts();
        assertEquals(2, products.size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() {

        ProductEntity product = new ProductEntity(1L, "testProduct", 100.0, 999);
        when(productService.getProductById(1L)).thenReturn(product);

        ProductEntity foundProduct = productResolver.getProductById(1L);
        assertNotNull(foundProduct);
        assertEquals("testProduct", foundProduct.getName());
        assertEquals(100.0, foundProduct.getPrice());
        assertEquals(999, foundProduct.getStockQuantity());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void testAddProduct() {

        ProductEntity product = new ProductEntity(1L, "newProduct", 86.0, 868);
        when(productService.addProduct(any(ProductEntity.class))).thenReturn(product);

        ProductEntity createdProduct = productResolver.addProduct("newProduct", 86.0, 868);
        assertNotNull(createdProduct);
        assertEquals("newProduct", createdProduct.getName());
        assertEquals(86.0, createdProduct.getPrice());
        assertEquals(868, createdProduct.getStockQuantity());
        verify(productService, times(1)).addProduct(any(ProductEntity.class));
    }
}
