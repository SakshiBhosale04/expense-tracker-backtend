package com.financetracker.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private String categoryName;
    private String categoryType;
    private Long userId;
}