package com.app.my.app.services;

import com.app.my.domain.models.Product;
import com.app.my.domain.repositories.ProductRepository;
import com.app.my.extern.controllers.ProductController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceTest {
    private MockMvc mockMvc;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    Product product1 = new Product(1L, "Молоко", "Вкусное молоко", 126);
    Product product2 = new Product(2L, "Хлеб", "Вкусный хлеб", 100);
    Product product3 = new Product(3L, "Шоколад", "Вкусный шоколад", 150);

    //Тестирование метода получения продуктов
    @Test
    public void getAllProducts() throws Exception {
        List<Product> products = new ArrayList<>(Arrays.asList(product1, product2, product3));

        Mockito.when(productRepository.findAll()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].title", is("Шоколад")))
                .andExpect(jsonPath("$[1].title", is("Хлеб")));
    }
    //Тестирование метода получения продукта по Id
    @Test
    public void getProductById() throws Exception {
        Mockito.when(productRepository.findById(product1.getId())).thenReturn(java.util.Optional.of(product1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/product/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[1].title", is("Хлеб")));
    }
    //Тестирование метода удаления продукта по Id
    @Test
    public void deleteProductById() throws Exception {
       Mockito.when(productRepository.findById(product2.getId())).thenReturn(Optional.of(product2));
       mockMvc.perform(MockMvcRequestBuilders
               .delete("/product/delete/1")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }
}
