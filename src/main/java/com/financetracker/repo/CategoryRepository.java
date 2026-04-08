package com.financetracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financetracker.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByUserId(Long userId);

    List<Category> findByUserIdAndCategorytype(Long userId, String categorytype);
}