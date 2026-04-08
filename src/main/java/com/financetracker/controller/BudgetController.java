//https://www.postman.com/release-notes/postman-app/

package com.financetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.financetracker.dto.BudgetsDto;
import com.financetracker.entity.Budget;
import com.financetracker.service.BudgetService;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping("/setbudgets")
    public List<Budget> setBudgets(@RequestBody List<BudgetsDto> dtos) {
        return budgetService.setBudgets(dtos);
    }
    // 2️⃣ Get budget by userId and category (already present)
    @GetMapping("/getinfo/{userId}/{category}")
    public String check(@PathVariable Long userId,
                        @PathVariable String category) {
        return budgetService.checkBudget(userId, category);
    }

    // 3️⃣ Get all budgets for a user
    @GetMapping("/user/{userId}")
    public List<Budget> getUserBudgets(@PathVariable Long userId) {
        return budgetService.getBudgetsByUser(userId);
    }

    // 4️⃣ Get budget by budgetId
    @GetMapping("/budget/{id}")
    public Budget getBudgetById(@PathVariable Long id) {
        return budgetService.getBudgetById(id);
    }

    // 5️⃣ Update a budget
    @PutMapping("/{id}")
    public Budget updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
        return budgetService.updateBudget(id, budget);
    }

    // 6️⃣ Delete a budget
    @DeleteMapping("/deleteid/{userId}")
    public String deleteBudget(@PathVariable Long userId) {
        budgetService.deleteBudget(userId);
        return "Budget with id " + userId + " deleted successfully";
    }
}