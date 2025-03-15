package com.example.seversteel.repository;

import com.example.seversteel.models.DeliveryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryItemRepository extends JpaRepository<DeliveryItem, Integer> {
}
