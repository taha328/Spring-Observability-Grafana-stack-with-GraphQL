package com.example.orderservice.resolver;

import com.example.orderservice.entities.OrderEntity;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderResolver {


    private final OrderService orderService;

    @Autowired
    public OrderResolver(OrderService orderService) {
        this.orderService = orderService;
    }

    @QueryMapping
    public List<OrderEntity> listOrders() {
        return orderService.getAllOrders();
    }

    @MutationMapping
    public OrderEntity createOrder(@Argument Long userId, @Argument Long productId, @Argument Integer quantity) {
        return orderService.createOrder(userId, productId, quantity);
    }
}
