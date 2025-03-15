package com.example.seversteel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private String supplierName;
    private String productName;
    private Integer totalWeight;
    private Integer totalCost;
}
