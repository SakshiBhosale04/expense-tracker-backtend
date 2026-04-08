package com.financetracker.service;

import java.util.Map;

import com.financetracker.entity.Dashboard;

public interface ReportService {

	Dashboard monthlyReport(Long userId, int month);

	Dashboard getYearlyReport(Long userId, int month);

	Dashboard getCategoryReport(Long userId, int month);

	Dashboard getCategoryReport(Long userId);

	Map<String, Double> monthlyReport(Long userId);

	Dashboard getYearlyReport(Long userId);
	
}