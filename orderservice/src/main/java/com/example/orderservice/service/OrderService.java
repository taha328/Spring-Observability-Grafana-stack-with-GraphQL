package com.example.orderservice.service;

import com.example.orderservice.client.ProductServiceClient;
import com.example.orderservice.client.UserServiceClient;
import com.example.orderservice.entities.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserServiceClient userServiceClient;
    private final ProductServiceClient productServiceClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserServiceClient userServiceClient, ProductServiceClient productServiceClient) {
        this.orderRepository = orderRepository;
        this.userServiceClient = userServiceClient;
        this.productServiceClient = productServiceClient;
    }

    public OrderEntity createOrder(Long userId, Long productId, Integer quantity) {
        // Validate user and product
        if (userServiceClient.getUserById(userId) == null) {
            throw new RuntimeException("User not found");
        }
        if (productServiceClient.getProductById(productId) == null) {
            throw new RuntimeException("Product not found");
        }

        // Create and save the order
        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(quantity);
        return orderRepository.save(order);
    }

}
