package com.ecom.productService.service;

import com.ecom.productService.dto.OrderEvent;
import com.ecom.productService.dto.OrderRequest;
import com.ecom.productService.modal.Product;
import com.ecom.productService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public String orderProduct(OrderRequest orderRequest) {
        Optional<Product> optionalProduct = productRepository.findById(orderRequest.getProductId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            product.setStock(product.getStock() - orderRequest.getQuantity());
            productRepository.save(product);

            // Publish order event to Kafka
            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setProductId(product.getId());
            orderEvent.setProductName(product.getName());
            orderEvent.setUserId(orderRequest.getUserId());
            orderEvent.setQuantity(orderRequest.getQuantity());
            orderEvent.setPrice(product.getPrice());

            kafkaTemplate.send("orderDetails", orderEvent);
            return "Order is successfully processed.";
        }
        return "Failed to processed order";
    }
}
