package com.ecom.userService.service;

import com.ecom.userService.dto.OrderEvent;
import com.ecom.userService.entity.OrderDetailsEntity;
import com.ecom.userService.entity.UserEntity;
import com.ecom.userService.repository.OrderDetailsRepository;
import com.ecom.userService.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailsRepository orderRepository;

    @Transactional
    public void storeOrderDetails(OrderEvent orderEvent) {

        Optional<UserEntity> user = userRepository.findByEmailId(orderEvent.getUserEmail());

        // save order details
        OrderDetailsEntity orderdetails = new OrderDetailsEntity();
        orderdetails.setProductId(orderEvent.getProductId());
        orderdetails.setPrice(orderEvent.getPrice());
        orderdetails.setQuantity(orderEvent.getQuantity());
        orderdetails.setProductName(orderEvent.getProductName());
        orderdetails.setUserEmail(orderEvent.getUserEmail());
        orderdetails.setUserId(user.get().getId());
        orderRepository.save(orderdetails);
    }


}
