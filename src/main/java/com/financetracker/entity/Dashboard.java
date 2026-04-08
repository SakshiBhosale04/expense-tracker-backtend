package com.financetracker.entity;

import java.util.Map;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dashboard")
public class Dashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalIncome;
    private double totalExpense;
    private double balance;
    private double totalBudget;
    private double remainingBudget;

    @ElementCollection
    @CollectionTable(name = "dashboard_category_summary", joinColumns = @JoinColumn(name = "dashboard_id"))
    @MapKeyColumn(name = "category")
    @Column(name = "amount")
    private Map<String, Double> categorySummary;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}