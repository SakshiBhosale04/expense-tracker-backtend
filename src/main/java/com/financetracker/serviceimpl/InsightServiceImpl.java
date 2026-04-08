package com.financetracker.serviceimpl;

import com.financetracker.entity.Transaction;
import com.financetracker.entity.TransactionType;
import com.financetracker.repo.TransactionRepository;
import com.financetracker.service.InsightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InsightServiceImpl implements InsightService {

    @Autowired
    private TransactionRepository transactionRepository;

    // 🔥 1. Analyze Spending (Already Good)
    @Override
    public String analyzeSpending(Long userId) {

        List<Transaction> transactions =
                transactionRepository.findByUserId(userId);

        Map<String, Double> categoryMap = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.EXPENSE) {
                categoryMap.put(
                        t.getCategory(),
                        categoryMap.getOrDefault(t.getCategory(), 0.0)
                                + t.getAmount()
                );
            }
        }

        String topCategory = "";
        double max = 0;

        for (Map.Entry<String, Double> entry : categoryMap.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                topCategory = entry.getKey();
            }
        }

        return "⚠️ Highest spending is on " + topCategory +
                " (₹" + max + "). Try reducing it.";
    }

    // 🔥 2. Spending by Category (Month)
    @Override
    public String getSpendingByCategory(Long userId, int month) {

        List<Transaction> transactions =
                transactionRepository.findByUserId(userId);

        Map<String, Double> map = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.EXPENSE &&
                t.getDate().getMonthValue() == month) {

                map.put(
                        t.getCategory(),
                        map.getOrDefault(t.getCategory(), 0.0)
                                + t.getAmount()
                );
            }
        }

        return map.toString();
    }

    // 🔥 3. Top Spending Category (Month)
    @Override
    public String getTopSpendingCategory(Long userId, int month) {

        List<Transaction> transactions =
                transactionRepository.findByUserId(userId);

        Map<String, Double> map = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.EXPENSE &&
                t.getDate().getMonthValue() == month) {

                map.put(
                        t.getCategory(),
                        map.getOrDefault(t.getCategory(), 0.0)
                                + t.getAmount()
                );
            }
        }

        String topCategory = "";
        double max = 0;

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                topCategory = entry.getKey();
            }
        }

        return "Top spending category: " + topCategory + " (₹" + max + ")";
    }

    // 🔥 4. Monthly Trend
    @Override
    public String getMonthlyTrend(Long userId) {

        List<Transaction> transactions =
                transactionRepository.findByUserId(userId);

        Map<Integer, Double> map = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.EXPENSE) {

                int month = t.getDate().getMonthValue();

                map.put(
                        month,
                        map.getOrDefault(month, 0.0)
                                + t.getAmount()
                );
            }
        }

        return map.toString();
    }

    // 🔥 5. Savings Insights
    @Override
    public String getSavingsInsights(Long userId, int month) {

        List<Transaction> transactions =
                transactionRepository.findByUserId(userId);

        double income = 0;
        double expense = 0;

        for (Transaction t : transactions) {

            if (t.getDate().getMonthValue() == month) {

                if (t.getType() == TransactionType.INCOME) {
                    income += t.getAmount();
                } else {
                    expense += t.getAmount();
                }
            }
        }

        double savings = income - expense;

        if (savings > 0) {
            return "✅ You saved ₹" + savings;
        } else {
            return "⚠️ You overspent ₹" + Math.abs(savings);
        }
    }

    // 🔥 6. Overspending Alerts
    @Override
    public String getOverspendingAlerts(Long userId, int month) {

        List<Transaction> transactions =
                transactionRepository.findByUserId(userId);

        double totalExpense = 0;

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.EXPENSE &&
                t.getDate().getMonthValue() == month) {

                totalExpense += t.getAmount();
            }
        }

        double limit = 10000; // you can connect this with Budget later

        if (totalExpense > limit) {
            return "⚠️ Alert! You exceeded ₹" + limit;
        } else {
            return "✅ Spending is under control";
        }
    }
}