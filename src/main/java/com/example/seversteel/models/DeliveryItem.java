package com.example.seversteel.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "delivery_item")
public class DeliveryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Delivery delivery;

    @ManyToOne
    private Product product;

    private Integer weight;
    private Integer totalCost;

    @PrePersist
    @PreUpdate
    private void calculateTotalCost() {
        if (product != null && weight != null) {
            totalCost = product.getPricePerKg() * weight;
        }
    }

}
