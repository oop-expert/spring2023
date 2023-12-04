package com.app.my.extern.controllers;

import com.app.my.app.services.CategoryService;
import com.app.my.domain.models.Category;
import com.app.my.domain.repositories.CategoryRepository;
import com.app.my.domain.repositories.CommentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    private final CategoryRepository categoryRepository;


    @GetMapping("/categories/create")
    public String createCategory() {
        return "categoriesCreate";
    }

    @PostMapping("/categories/create")
    public String categoryCreate(@RequestParam String name,  Model model) throws IOException {
        Category category = new Category(name);
        categoryService.saveCategory(category);
        return "redirect:/admin";
    }

    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) throws IOException {
        categoryRepository.deleteById(id);
        return "redirect:/admin";
    }
}
