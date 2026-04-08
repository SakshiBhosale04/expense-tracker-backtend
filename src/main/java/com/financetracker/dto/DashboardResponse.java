package com.financetracker.dto;

public class DashboardResponse {
	private double totalIncome;
	private double totalExpense;
	private double balance;
	private double totalBudget;
	private double remainingBudget;

	// Default Constructor
	public DashboardResponse() {
	}

	// Parameterized Constructor
	public DashboardResponse(double totalIncome, double totalExpense, double balance, double totalBudget,
			double remainingBudget) {
		this.totalIncome = totalIncome;
		this.totalExpense = totalExpense;
		this.balance = balance;
		this.totalBudget = totalBudget;
		this.remainingBudget = remainingBudget;
	}

	// Getters and Setters

	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(double totalExpense) {
		this.totalExpense = totalExpense;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(double totalBudget) {
		this.totalBudget = totalBudget;
	}

	public double getRemainingBudget() {
		return remainingBudget;
	}

	public void setRemainingBudget(double remainingBudget) {
		this.remainingBudget = remainingBudget;
	}
}
