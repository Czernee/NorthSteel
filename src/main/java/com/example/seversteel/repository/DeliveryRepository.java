package com.example.seversteel.repository;

import com.example.seversteel.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    List<Delivery> findByDeliveryDateBetween(LocalDate startDate, LocalDate endDate);
}
