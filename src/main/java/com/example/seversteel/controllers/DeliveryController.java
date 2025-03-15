package com.example.seversteel.controllers;

import com.example.seversteel.dto.DeliveryRequest;
import com.example.seversteel.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<?> createDelivery(@RequestBody DeliveryRequest request) {
        deliveryService.createDelivery(request);
        return ResponseEntity.ok().build();
    }
}
