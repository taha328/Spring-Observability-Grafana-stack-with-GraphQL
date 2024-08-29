package com.example.productservice.service;



import com.example.productservice.entities.ProductEntity;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity addProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    public List<ProductEntity> getAllProduct() {
        return productRepository.findAll();

    // Add other methods as needed (e.g., update, delete)
}}
