package com.financetracker.service;

import com.financetracker.dto.BudgetsDto;
import com.financetracker.entity.Budget;
import java.util.List;

public interface BudgetService {

	// Create a budget
	Budget setBudget(BudgetsDto dto);

	// Check budget for a user and category
	String checkBudget(Long userId, String category);

	// Get all budgets for a user
	List<Budget> getBudgetsByUser(Long userId);

	// Get budget by ID
	Budget getBudgetById(Long id);

	// Update a budget
	Budget updateBudget(Long id, Budget budget);

	// Delete a budget
	void deleteBudget(Long userId);

	List<Budget> setBudgets(List<BudgetsDto> dtos);
}