package com.example.orderservice.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {

    private Long userId;
    private Long productId;
    private Integer quantity;

}
