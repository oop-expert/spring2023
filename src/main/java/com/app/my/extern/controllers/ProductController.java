package com.app.my.extern.controllers;

import com.app.my.app.services.ProductService;
import com.app.my.domain.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    //Получаем страницу с товарами и формой для создания товара
    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }

    //Получаем подробную информацию об определенном товаре
    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "productInfo";
    }

    //Создаем товар из данных, собранных в форме
    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product, file1, file2, file3);
        return "redirect:/";
    }

    //Метод удаления товара по id
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}