package com.ecom.userService.dto;

import lombok.Data;

@Data
public class OrderEvent {
    private String productId;
    private String productName;
    private String userId;
    private String userEmail;
    private Integer quantity;
    private Double price;
}
