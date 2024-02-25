package com.ecom.userService.kafka;

import com.ecom.userService.dto.OrderEvent;
import com.ecom.userService.service.OrderService;
import com.ecom.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "orderDetails", groupId = "user-microservice-group")
    public void consume(OrderEvent orderEvent) {
        orderService.storeOrderDetails(orderEvent);
    }
}
