package com.example.seversteel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequest {
    private Integer supplierId;
    private LocalDate deliveryDate;
    private List<DeliveryItemRequest> items;
}
