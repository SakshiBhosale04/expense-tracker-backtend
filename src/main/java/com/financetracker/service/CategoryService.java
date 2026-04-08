package com.financetracker.service;

import java.util.List;

import com.financetracker.entity.Category;

public interface CategoryService {

	List<Category> getAllCategories(Long id);

	List<Category> categorytype(Long id, String categorytype);

	Category updateCategory(Long id, String category);

	Category deleteCategorya(Long id);

	Category addCategory(Category category, Long userId);

	
}
