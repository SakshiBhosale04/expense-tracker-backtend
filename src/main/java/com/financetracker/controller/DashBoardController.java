package com.financetracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financetracker.dto.DashboardDto;
import com.financetracker.dto.DashboardSummaryResponse;
import com.financetracker.dto.TransactionDTO;
import com.financetracker.entity.Dashboard;
import com.financetracker.entity.Transaction;
import com.financetracker.service.DashboardService;
import com.financetracker.service.TransactionService;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/add-expense")
	public ResponseEntity<?> addExpense(@RequestBody TransactionDTO dto) {
	    return ResponseEntity.ok(transactionService.addTransaction(dto));
	}
	@PostMapping("/add-income")
	public List<Dashboard> addIncome(@RequestBody List<DashboardDto> dtos) {
		return dashboardService.saveAll(dtos);
	}

	// Accept month as name or number
	@GetMapping("/{userId}/{month}")
	public DashboardSummaryResponse getDashboardSummary(
	        @PathVariable Long userId,
	        @PathVariable String month) {

	    int monthNumber = parseMonth(month);
	    return dashboardService.getDashboardSummary(userId, monthNumber);
	}

	@GetMapping("/{userId}/{month}/income")
	public Dashboard getTotalIncome(@PathVariable Long userId, @PathVariable String month) {
		int monthNumber = parseMonth(month);
		return dashboardService.getTotalIncome(userId, monthNumber);
	}

	@GetMapping("/{userId}/{month}/expense")
	public Dashboard getTotalExpense(@PathVariable Long userId, @PathVariable String month) {
		int monthNumber = parseMonth(month);
		return dashboardService.getTotalExpense(userId, monthNumber);
	}

	@GetMapping("/{userId}/{month}/balance")
	public Dashboard getBalance(@PathVariable Long userId, @PathVariable String month) {
		int monthNumber = parseMonth(month);
		return dashboardService.getBalance(userId, monthNumber);
	}

	@GetMapping("/{userId}/{month}/budget")
	public Dashboard getBudgetStatus(@PathVariable Long userId, @PathVariable String month) {
		int monthNumber = parseMonth(month);
		return dashboardService.getBudgetStatus(userId, monthNumber);
	}

	// Helper method to convert month name to number
	private int parseMonth(String month) {
		try {
			return java.time.Month.valueOf(month.toUpperCase()).getValue();
		} catch (IllegalArgumentException e) {
			try {
				// fallback: parse as integer
				return Integer.parseInt(month);
			} catch (NumberFormatException ex) {
				throw new RuntimeException("Invalid month: " + month);
			}
		}
	}
}