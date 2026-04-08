package com.financetracker.dto;

import lombok.Data;

@Data
public class TransactionDTO {
	private Double amount;
	private String type; // INCOME / EXPENSE
	private String category;
	private String date; // keep string but must be "YYYY-MM-DD"
	private String description;
	private Long userId;
}