package com.example.seversteel.service;

import com.example.seversteel.dto.ReportResponse;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<ReportResponse> generateReport(LocalDate startDate, LocalDate endDate);
}
