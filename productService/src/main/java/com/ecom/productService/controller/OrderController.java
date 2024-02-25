package com.ecom.productService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.productService.dto.OrderRequest;
import com.ecom.productService.service.OrderService;

@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    OrderService orderService;

    @PostMapping("/create-order")
    public String orderProduct(@RequestBody OrderRequest orderReq){
        return orderService.orderProduct(orderReq);
    }
}
