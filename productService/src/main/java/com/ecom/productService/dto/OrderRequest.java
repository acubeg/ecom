package com.ecom.productService.dto;

import lombok.Data;

@Data
public class OrderRequest {

    private String productId;
    private String productName;
    private Integer quantity;
    private String userId;
    private String userEmail;
}