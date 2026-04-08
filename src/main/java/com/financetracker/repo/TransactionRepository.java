package com.financetracker.repo;

import com.financetracker.entity.Transaction;
import com.financetracker.entity.TransactionType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	// ✅ REQUIRED (used everywhere)
	List<Transaction> findByUserId(Long userId);

	// ✅ OPTIONAL (if you want object-based query)
	// List<Transaction> findByUser(User user);

	// ✅ Total expense
	@Query("SELECT SUM(t.amount) FROM Transaction t " + "WHERE t.user.id = :userId AND t.type = 'EXPENSE'")
	Double getTotalExpense(Long userId);
	
	
}