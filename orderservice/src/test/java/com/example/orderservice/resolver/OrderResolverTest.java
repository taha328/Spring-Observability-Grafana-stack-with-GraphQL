package com.example.orderservice.resolver;

import com.example.orderservice.entities.OrderEntity;
import com.example.orderservice.service.OrderService;
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

class OrderResolverTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderResolver orderResolver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListOrders() {
        OrderEntity order1 = new OrderEntity();
        order1.setUserId(1L);
        order1.setProductId(1L);
        order1.setQuantity(2);

        OrderEntity order2 = new OrderEntity();
        order2.setUserId(2L);
        order2.setProductId(2L);
        order2.setQuantity(3);

        when(orderService.getAllOrders()).thenReturn(Arrays.asList(order1, order2));

        List<OrderEntity> orders = orderResolver.listOrders();
        assertEquals(2, orders.size());
        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    void testCreateOrder() {
        OrderEntity order = new OrderEntity();
        order.setUserId(1L);
        order.setProductId(1L);
        order.setQuantity(2);

        when(orderService.createOrder(1L, 1L, 2)).thenReturn(order);

        OrderEntity createdOrder = orderResolver.createOrder(1L, 1L, 2);
        assertNotNull(createdOrder);
        assertEquals(1L, createdOrder.getUserId());
        assertEquals(1L, createdOrder.getProductId());
        assertEquals(2, createdOrder.getQuantity());
        verify(orderService, times(1)).createOrder(1L, 1L, 2);
    }
}
