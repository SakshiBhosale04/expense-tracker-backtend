package com.financetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financetracker.service.InsightService;

@RestController
@RequestMapping("/insights")
public class InsighController {

    @Autowired
    private InsightService insightService;

    @GetMapping("/{userId}/{month}/category")
    public String getSpendingByCategory(@PathVariable Long userId,
                                        @PathVariable int month) {
        return insightService.getSpendingByCategory(userId, month);
    }

    @GetMapping("/{userId}/{month}/top-category")
    public String getTopSpendingCategory(@PathVariable Long userId,
                                         @PathVariable int month) {
        return insightService.getTopSpendingCategory(userId, month);
    }

    @GetMapping("/{userId}/trend")
    public String getMonthlyTrend(@PathVariable Long userId) {
        return insightService.getMonthlyTrend(userId);
    }

    @GetMapping("/{userId}/{month}/savings")
    public String getSavingsInsights(@PathVariable Long userId,
                                     @PathVariable int month) {
        return insightService.getSavingsInsights(userId, month);
    }

    @GetMapping("/{userId}/{month}/alerts")
    public String getOverspendingAlerts(@PathVariable Long userId,
                                        @PathVariable int month) {
        return insightService.getOverspendingAlerts(userId, month);
    }
}