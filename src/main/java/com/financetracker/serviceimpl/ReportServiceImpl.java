package com.financetracker.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financetracker.entity.Dashboard;
import com.financetracker.entity.Transaction;
import com.financetracker.entity.TransactionType;
import com.financetracker.repo.TransactionRepository;
import com.financetracker.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Override
	public Dashboard monthlyReport(Long userId, int month) {

		List<Transaction> transactions = transactionRepository.findByUserId(userId);

		double income = 0;
		double expense = 0;

		for (Transaction t : transactions) {
			if (t.getDate().getMonthValue() == month) {

				if (t.getType().equalsIgnoreCase("income")) {
					income += t.getAmount();
				} else {
					expense += t.getAmount();
				}
			}
		}

		Dashboard dashboard = new Dashboard();
		dashboard.setTotalIncome(income);
		dashboard.setTotalExpense(expense);
		dashboard.setBalance(income - expense);

		return dashboard;
	}

	@Override
	public Dashboard getYearlyReport(Long userId, int year) {

		List<Transaction> transactions = transactionRepository.findByUserId(userId);

		double income = 0;
		double expense = 0;

		for (Transaction t : transactions) {

			if (t.getDate().getYear() == year) {

				if (t.getType() == TransactionType.INCOME) {
					income += t.getAmount();
				} else {
					expense += t.getAmount();
				}
			}
		}

		Dashboard dashboard = new Dashboard();
		dashboard.setTotalIncome(income);
		dashboard.setTotalExpense(expense);
		dashboard.setBalance(income - expense);

		return dashboard;
	}

	@Override
	public Dashboard getCategoryReport(Long userId, int month) {

		List<Transaction> transactions = transactionRepository.findByUserId(userId);

		Map<String, Double> categoryMap = new HashMap<>();

		for (Transaction t : transactions) {

			if (t.getDate().getMonthValue() == month) {

				String category = t.getCategory();

				categoryMap.put(category, categoryMap.getOrDefault(category, 0.0) + t.getAmount());
			}
		}

		Dashboard dashboard = new Dashboard();
		dashboard.setCategorySummary(categoryMap);

		return dashboard;
	}

	@Override
	public Dashboard getCategoryReport(Long userId) {

		List<Transaction> transactions = transactionRepository.findByUserId(userId);

		Map<String, Double> categoryMap = new HashMap<>();

		for (Transaction t : transactions) {

			String category = t.getCategory();

			categoryMap.put(category, categoryMap.getOrDefault(category, 0.0) + t.getAmount());
		}

		Dashboard dashboard = new Dashboard();
		dashboard.setCategorySummary(categoryMap);

		return dashboard;
	}

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Map<String, Double> monthlyReport(Long userId) {

		List<Transaction> transactions = transactionRepository.findByUserId(userId);

		Map<String, Double> data = new HashMap<>();

		for (Transaction t : transactions) {
			String month = t.getDate().getMonth().toString();

			data.put(month, data.getOrDefault(month, 0.0) + t.getAmount());
		}

		return data;
	}

	@Override
	public Dashboard getYearlyReport(Long userId) {

	    List<Transaction> transactions = transactionRepository.findByUserId(userId);

	    double totalIncome = 0;
	    double totalExpense = 0;

	    for (Transaction t : transactions) {

	        if (t.getType() == TransactionType.INCOME) {
	            totalIncome += t.getAmount();
	        }

	        else if (t.getType() == TransactionType.EXPENSE) {
	            totalExpense += t.getAmount();
	        }
	    }

	    double balance = totalIncome - totalExpense;

	    Dashboard dashboard = new Dashboard();
	    dashboard.setTotalIncome(totalIncome);
	    dashboard.setTotalExpense(totalExpense);
	    dashboard.setBalance(balance);

	    return dashboard;
	}
}