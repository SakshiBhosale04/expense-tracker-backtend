package com.financetracker.serviceimpl;

import com.financetracker.dto.BudgetsDto;
import com.financetracker.entity.Budget;
import com.financetracker.entity.User;
import com.financetracker.repo.BudgetRepository;
import com.financetracker.repo.UserRepository;
import com.financetracker.service.BudgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;

    // Single budget creation
    @Override
    public Budget setBudget(BudgetsDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Budget budget = new Budget();
        budget.setCategory(dto.getCategory());
        budget.setLimitAmount(dto.getLimitAmount());
        budget.setDate(dto.getDate()); // directly use LocalDate
        budget.setUser(user);

        return budgetRepository.save(budget);
    }

    // Batch budgets creation
    @Override
    public List<Budget> setBudgets(List<BudgetsDto> dtos) {
        List<Budget> budgets = new ArrayList<>();

        for (BudgetsDto dto : dtos) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Budget budget = new Budget();
            budget.setCategory(dto.getCategory());
            budget.setLimitAmount(dto.getLimitAmount());
            budget.setDate(dto.getDate()); // exact date
            budget.setUser(user);

            budgets.add(budget);
        }

        return budgetRepository.saveAll(budgets);
    }

    // Check budget for a user & category
    @Override
    public String checkBudget(Long userId, String category) {
        List<Budget> budgets = budgetRepository.findByUserIdAndCategory(userId, category);
        if (budgets.isEmpty()) {
            return "No budget set for this category";
        }
        Budget budget = budgets.get(0); // first budget
        return "Budget for " + category + " on " + budget.getDate() + ": " + budget.getLimitAmount();
    }

    // Get all budgets for a user
    @Override
    public List<Budget> getBudgetsByUser(Long userId) {
        return budgetRepository.findByUserId(userId);
    }

    // Get budget by ID
    @Override
    public Budget getBudgetById(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found with id " + id));
    }

    // Update a budget
    @Override
    public Budget updateBudget(Long id, Budget budget) {
        Budget existing = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found with id " + id));

        existing.setCategory(budget.getCategory());
        existing.setLimitAmount(budget.getLimitAmount());
        existing.setDate(budget.getDate());

        if (budget.getUser() != null) {
            Optional<User> user = userRepository.findById(budget.getUser().getId());
            user.ifPresent(existing::setUser);
        }

        return budgetRepository.save(existing);
    }

    // Delete budget by ID
    @Override
    public void deleteBudget(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found with id " + id));
        budgetRepository.delete(budget);
    }
}