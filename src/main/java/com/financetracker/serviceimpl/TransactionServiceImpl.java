package com.financetracker.serviceimpl;

import com.financetracker.dto.TransactionDTO;
import com.financetracker.entity.Transaction;
import com.financetracker.entity.TransactionType;
import com.financetracker.entity.User;
import com.financetracker.repo.TransactionRepository;
import com.financetracker.repo.UserRepository;
import com.financetracker.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UserRepository userRepository;

	// ADD
	@Override
    public Transaction addTransaction(TransactionDTO dto) {

        // Fetch user
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setType(TransactionType.valueOf(dto.getType()));
        transaction.setCategory(dto.getCategory());
        transaction.setDate(LocalDate.parse(dto.getDate()));
        transaction.setDescription(dto.getDescription());

        // Link user
        transaction.setUser(user);

        return transactionRepository.save(transaction);
    }

	// GET ALL BY USER
	@Override
	public List<Transaction> getAllTransaction(Long userId) {
		return transactionRepository.findByUserId(userId);
	}

	// GET BY ID
	@Override
	public Transaction getTransactionById(Long id) {
		return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
	}

	// GET BY MONTH
	@Override
	public List<Transaction> getTransactionsByMonth(Long userId, int month) {

		List<Transaction> all = transactionRepository.findByUserId(userId);
		List<Transaction> result = new ArrayList<>();

		for (Transaction t : all) {
			if (t.getDate().getMonthValue() == month) {
				result.add(t);
			}
		}

		return result;
	}

	// UPDATE
	@Override
	public Transaction updateTransaction(Long id, Transaction updatedTransaction) {

		Transaction existing = transactionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transaction not found"));

		existing.setAmount(updatedTransaction.getAmount());
		existing.setCategory(updatedTransaction.getCategory());
		existing.setDate(updatedTransaction.getDate());
		existing.setType(updatedTransaction.getType());

		return transactionRepository.save(existing);
	}

	// DELETE
	@Override
	public void deleteTransaction(Long id) {

		Transaction existing = transactionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transaction not found"));

		transactionRepository.delete(existing);
	}

	@Override
	public List<Transaction> getByUser(Long userId) {
		return transactionRepository.findByUserId(userId);
	}

	@Override
	public Double getTotalExpense(Long userId) {

		List<Transaction> transactions = transactionRepository.findByUserId(userId);

		double total = 0;

		for (Transaction t : transactions) {
			if (t.getType().name().equals("EXPENSE")) {
				total += t.getAmount();
			}
		}

		return total;
	}

	@Override
	public List<Transaction> saveAllTransactions(List<Transaction> transactions) {

	    for (Transaction t : transactions) {
	        if (t.getUser() == null || t.getUser().getId() == null) {
	            throw new RuntimeException("User ID is required for each transaction");
	        }
	    }

	    return transactionRepository.saveAll(transactions);
	}
	
}