package com.app.my.domain.repositories;

import com.app.my.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);
    void deleteCategoryById(Long id);
}
