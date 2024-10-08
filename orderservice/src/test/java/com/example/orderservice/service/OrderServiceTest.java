package com.example.orderservice.service;

import com.example.orderservice.entities.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import com.example.productservice.entities.ProductEntity;
import com.example.userservice.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        UserEntity mockUser = new UserEntity(1L, "testUser");
        ProductEntity mockProduct = new ProductEntity(1L, "testProduct", 100.0, 10);

        when(restTemplate.exchange(eq("http://localhost:8081/graphql"), eq(HttpMethod.POST), any(HttpEntity.class), eq(UserEntity.class)))
                .thenReturn(ResponseEntity.ok(mockUser));

        when(restTemplate.exchange(eq("http://localhost:8082/graphql"), eq(HttpMethod.POST), any(HttpEntity.class), eq(ProductEntity.class)))
                .thenReturn(ResponseEntity.ok(mockProduct));

        OrderEntity mockOrder = new OrderEntity();
        mockOrder.setUserId(1L);
        mockOrder.setProductId(1L);
        mockOrder.setQuantity(2);
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(mockOrder);

        OrderEntity createdOrder = orderService.createOrder(1L, 1L, 2);

        assertNotNull(createdOrder);
        assertEquals(1L, createdOrder.getUserId());
        assertEquals(1L, createdOrder.getProductId());
        assertEquals(2, createdOrder.getQuantity());
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    void testCreateOrderUserNotFound() {
        when(restTemplate.exchange(eq("http://localhost:8081/graphql"), eq(HttpMethod.POST), any(HttpEntity.class), eq(UserEntity.class)))
                .thenReturn(ResponseEntity.ok(null));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(1L, 1L, 2);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testCreateOrderProductNotFound() {
        UserEntity mockUser = new UserEntity(1L, "testUser");
        when(restTemplate.exchange(eq("http://localhost:8081/graphql"), eq(HttpMethod.POST), any(HttpEntity.class), eq(UserEntity.class)))
                .thenReturn(ResponseEntity.ok(mockUser));

        when(restTemplate.exchange(eq("http://localhost:8082/graphql"), eq(HttpMethod.POST), any(HttpEntity.class), eq(ProductEntity.class)))
                .thenReturn(ResponseEntity.ok(null));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(1L, 1L, 2);
        });

        assertEquals("Product not found", exception.getMessage());
    }


}
