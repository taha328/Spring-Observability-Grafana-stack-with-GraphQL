package com.example.productservice.resolver;

import com.example.productservice.entities.ProductEntity;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductResolver {

    private final ProductService productService;

    @Autowired
    public ProductResolver(ProductService productService) {
        this.productService = productService;
    }


    @QueryMapping
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }
    @QueryMapping
    public ProductEntity getProductById(@Argument long id) {
        return productService.getProductById(id);
    }

    @MutationMapping
    public ProductEntity addProduct(@Argument String name, @Argument Double price, @Argument Integer stockQuantity) {
        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        return productService.addProduct(product);
    }
}
