package com.app.my.app.services;

import com.app.my.domain.models.Category;
import com.app.my.domain.models.Product;
import com.app.my.domain.models.User;
import com.app.my.domain.repositories.CategoryRepository;
import com.app.my.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     *Метод возвращает все категории, которые имееются
     */
    public List<Category> listCategories(String name) {
        if (name != null) return categoryRepository.findByName(name);
        return categoryRepository.findAll();
    }

    /**
     * Метод сохраняет категорию
     * @param category - категория, которую мы хотим сохранить
     */

    public void saveCategory(Category category) throws IOException {
        log.info("Saving new category: {} ", category.getName());
        categoryRepository.save(category);
    }

    /**
     * Метод удаляет категорию
     * @param id - категория, которую мы хотим удалить
     */
    public void deleteCategory(Long id) throws IOException{
        log.info("Deleted category");
        categoryRepository.deleteCategoryById(id);
    }

    /**
     * Метод возвращает все категории
     */
    public List<Category> list() {
        return categoryRepository.findAll();
    }


}
