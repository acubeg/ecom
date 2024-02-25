package com.ecom.productService.service;


import com.ecom.productService.dto.OrderEvent;
import com.ecom.productService.modal.Product;
import com.ecom.productService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Other methods...

    public boolean orderProduct(Long userId, String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // Publish order event to Kafka
            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setProductId(product.getId());
            orderEvent.setProductName(product.getName());
            orderEvent.setUserId(userId);

            kafkaTemplate.send("orderDetails", orderEvent);

            return true;
        }
        return false;
    }
}

