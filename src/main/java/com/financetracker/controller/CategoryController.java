package com.financetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financetracker.entity.Category;
import com.financetracker.service.CategoryService;

@RestController
@RequestMapping("/category") // lowercase best practice
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // ✅ ADD
    @PostMapping("/addcategory")
    public Category addCategory(@RequestBody Category category,
                                @RequestParam Long userId) {
        return categoryService.addCategory(category, userId);
    }

    // ✅ GET ALL
    @GetMapping("/{userId}")
    public List<Category> getAll(@PathVariable Long userId) {
        return categoryService.getAllCategories(userId);
    }

    // ✅ GET BY TYPE
    @GetMapping("/{userId}/type/{type}")
    public List<Category> getByType(@PathVariable Long userId,
                                   @PathVariable String type) {
        return categoryService.categorytype(userId, type);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id,
                           @RequestParam String name) {
        return categoryService.updateCategory(id, name);
    }

    // ✅ DELETE ONE
    @DeleteMapping("/{id}")
    public Category delete(@PathVariable Long id) {
        return categoryService.deleteCategorya(id);
    }


}