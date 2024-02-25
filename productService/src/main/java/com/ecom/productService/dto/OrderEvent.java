package com.ecom.productService.dto;

import lombok.Data;

@Data
public class OrderEvent {
    private String productId;
    private String productName;
    private Long userId;
    private String userEmail;
}
