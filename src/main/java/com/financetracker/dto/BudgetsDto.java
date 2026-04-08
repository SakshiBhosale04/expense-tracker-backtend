package com.financetracker.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetsDto {
    private Long userId;
    private String category;
    private Double limitAmount;
    private LocalDate date; // Use LocalDate instead of String
}