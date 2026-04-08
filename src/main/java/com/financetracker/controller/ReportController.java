package com.financetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financetracker.entity.Dashboard;
import com.financetracker.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/{userId}/{month}/monthly")
    public Dashboard getMonthlyReport(@PathVariable Long userId,
                                      @PathVariable int month) {
        return reportService.monthlyReport(userId, month);
    }

    @GetMapping("/{userId}/yearly")
    public Dashboard getYearlyReport(@PathVariable Long userId) {
        return reportService.getYearlyReport(userId);
    }

    @GetMapping("/{userId}/{month}/category")
    public Dashboard getCategoryReport(@PathVariable Long userId,
                                       @PathVariable int month) {
        return reportService.getCategoryReport(userId, month);
    }

    @GetMapping("/{userId}/transactions")
    public Dashboard getTransactionReport(@PathVariable Long userId) {
        return reportService.getCategoryReport(userId);
    }
}
