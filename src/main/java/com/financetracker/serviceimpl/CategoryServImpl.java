package com.financetracker.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financetracker.entity.Category;
import com.financetracker.entity.User;
import com.financetracker.repo.CategoryRepository;
import com.financetracker.repo.UserRepository;
import com.financetracker.service.CategoryService;

@Service

public class CategoryServImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ ADD CATEGORY
    @Override
    public Category addCategory(Category category, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        category.setUser(user);

        return categoryRepository.save(category);
    }

    // ✅ GET ALL
    @Override
    public List<Category> getAllCategories(Long userId) {
        return categoryRepository.findByUserId(userId);
    }

    // ✅ GET BY TYPE (FIXED)
    @Override
    public List<Category> categorytype(Long userId, String type) {
        return categoryRepository.findByUserIdAndCategorytype(userId, type);
    }

    // ✅ UPDATE
    @Override
    public Category updateCategory(Long id, String name) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setCategoryname(name);

        return categoryRepository.save(category);
    }

    // ✅ DELETE ONE
    @Override
    public Category deleteCategorya(Long userId) {

        Category category = categoryRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);

        return category;
    }


   
}