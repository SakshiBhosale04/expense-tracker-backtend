package com.financetracker.entity;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double amount;

	@Enumerated(EnumType.STRING)
	private TransactionType type;

	private String category;

	private LocalDate date;

	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}