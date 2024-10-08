package com.example.orderservice.service;

import com.example.orderservice.entities.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import com.example.productservice.entities.ProductEntity;
import com.example.userservice.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private OrderRepository orderRepository;
    private RestTemplate restTemplate;

    public OrderService() {

    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OrderEntity createOrder(Long userId, Long productId, Integer quantity) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);


        String userQuery = "{\"query\":\"{ getUserById(id: " + userId + ") { id, username } }\"}";
        String productQuery = "{\"query\":\"{ getProductById(id: " + productId + ") { id, name, price, stockQuantity } }\"}";

        HttpEntity<String> userRequest = new HttpEntity<>(userQuery, headers);
        HttpEntity<String> productRequest = new HttpEntity<>(productQuery, headers);


        ResponseEntity<UserEntity> userResponse = restTemplate.exchange(
                "http://localhost:8081/graphql",
                HttpMethod.POST,
                userRequest,
                UserEntity.class
        );
        UserEntity user = userResponse.getBody();


        ResponseEntity<ProductEntity> productResponse = restTemplate.exchange(
                "http://localhost:8082/graphql",
                HttpMethod.POST,
                productRequest,
                ProductEntity.class
        );
        ProductEntity product = productResponse.getBody();

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(quantity);
        return orderRepository.save(order);
    }
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
}
