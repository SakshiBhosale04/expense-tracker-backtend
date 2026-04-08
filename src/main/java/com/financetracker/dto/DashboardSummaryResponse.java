package com.financetracker.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardSummaryResponse {
    private double totalIncome;
    private double totalExpense;
    private double balance;
    private double totalBudget;
    private double remainingBudget;
    private Map<String, Double> categorySummary;
}