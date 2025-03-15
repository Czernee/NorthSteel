package com.example.seversteel.service.impl;

import com.example.seversteel.dto.ReportResponse;
import com.example.seversteel.models.Delivery;
import com.example.seversteel.models.DeliveryItem;
import com.example.seversteel.repository.DeliveryRepository;
import com.example.seversteel.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final DeliveryRepository deliveryRepository;

    public ReportServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<ReportResponse> generateReport(LocalDate startDate, LocalDate endDate) {
        List<Delivery> deliveries = deliveryRepository.findByDeliveryDateBetween(startDate, endDate);

        Map<String, Map<String, ReportResponse>> reportMap = new HashMap<>();

        for (Delivery delivery : deliveries) {
            String supplierName = delivery.getSupplier().getName();

            for (DeliveryItem item : delivery.getItems()) {
                String productName = item.getProduct().getName();
                Integer weight = item.getWeight();
                Integer totalCost = item.getTotalCost();

                reportMap.putIfAbsent(supplierName, new HashMap<>());

                ReportResponse report = reportMap.get(supplierName).getOrDefault(productName,
                        new ReportResponse(supplierName, productName, 0, 0));

                report.setTotalWeight(report.getTotalWeight() + weight);
                report.setTotalCost(report.getTotalCost() + totalCost);

                reportMap.get(supplierName).put(productName, report);
            }
        }

        List<ReportResponse> reportList = new ArrayList<>();
        for (Map<String, ReportResponse> productMap : reportMap.values()) {
            reportList.addAll(productMap.values());
        }

        return reportList;
    }
}
