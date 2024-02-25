package com.ecom.userService.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "ORDER_DETAILS")
@Data
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_email")
    private String userEmail;
}
