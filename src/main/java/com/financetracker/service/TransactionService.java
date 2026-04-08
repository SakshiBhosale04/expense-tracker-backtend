package com.financetracker.service;

import com.financetracker.dto.TransactionDTO;
import com.financetracker.entity.Transaction;

import java.util.List;

public interface TransactionService {


	    List<Transaction> getByUser(Long userId);

	    Double getTotalExpense(Long userId);

		List<Transaction> getAllTransaction(Long userId);

		Transaction getTransactionById(Long id);

		List<Transaction> getTransactionsByMonth(Long userId, int month);

		Transaction updateTransaction(Long id, Transaction transaction);

		void deleteTransaction(Long id);

		Transaction addTransaction(TransactionDTO dto);

		List<Transaction> saveAllTransactions(List<Transaction> transactions);

		static Object save(Transaction transaction) {
			// TODO Auto-generated method stub
			return null;
		}
	}
