package com.financetracker.service;

import java.util.List;
import java.util.Map;

import com.financetracker.dto.DashboardDto;
import com.financetracker.dto.DashboardResponse;
import com.financetracker.dto.DashboardSummaryResponse;
import com.financetracker.entity.Dashboard;

public interface DashboardService {

	DashboardSummaryResponse getDashboardSummary(Long userId, int monthNumber);

	Dashboard getTotalIncome(Long userId, int monthNumber);

	Dashboard getTotalExpense(Long userId, int month);

	Dashboard getBalance(Long userId, int month);

	Dashboard getBudgetStatus(Long userId, int month);

	DashboardResponse addTotalIncome(DashboardDto dto);

	List<Dashboard> saveAll(List<DashboardDto> dashboards);

}