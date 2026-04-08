package com.financetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financetracker.dto.TransactionDTO;
import com.financetracker.entity.Transaction;
import com.financetracker.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/addalltransaction")
	public List<Transaction> createTransactions(@RequestBody List<Transaction> transactions) {
		return transactionService.saveAllTransactions(transactions);
	}

	@PostMapping("/addtransaction")
	public Transaction addTransaction(@RequestBody TransactionDTO dto) {
		return transactionService.addTransaction(dto);
	}

	@GetMapping("/user/{userId}")
	public List<Transaction> getAllTransaction(@PathVariable Long userId) {
		return transactionService.getAllTransaction(userId);
	}

	@GetMapping("/id/{id}")
	public Transaction getTransactionById(@PathVariable Long id) {
		return transactionService.getTransactionById(id);
	}

	@GetMapping("/user/{userId}/month/{month}")
	public List<Transaction> getTransactionsByMonth(@PathVariable Long userId, @PathVariable int month) {
		return transactionService.getTransactionsByMonth(userId, month);
	}

	@PutMapping("/{id}")
	public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
		return transactionService.updateTransaction(id, transaction);
	}

	@DeleteMapping("/{id}")
	public String deleteTransaction(@PathVariable Long id) {
		transactionService.deleteTransaction(id);
		return "Transaction deleted successfully";
	}

}