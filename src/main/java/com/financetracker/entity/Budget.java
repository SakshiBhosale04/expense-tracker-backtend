package com.financetracker.entity;

import java.time.LocalDate;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "budgets")
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String category;

	private Double limitAmount;

	 private LocalDate date; // 1 to 12

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "month")
	private String month;

	public void setDate(LocalDate date) {
	    this.date = date;
	    // Properly format month with first letter uppercase and rest lowercase
	    this.month = date.getMonth().name().substring(0,1) 
	                 + date.getMonth().name().substring(1).toLowerCase();
	}
	
}