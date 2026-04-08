package com.financetracker.service;

public interface InsightService {
	String analyzeSpending(Long userId);

	String getSpendingByCategory(Long userId, int month);

	String getTopSpendingCategory(Long userId, int month);

	String getMonthlyTrend(Long userId);

	String getSavingsInsights(Long userId, int month);

	String getOverspendingAlerts(Long userId, int month);
}