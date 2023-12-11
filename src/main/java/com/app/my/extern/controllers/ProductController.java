package com.app.my.extern.controllers;

import com.app.my.app.services.CommentService;
import com.app.my.app.services.ProductService;
import com.app.my.domain.models.Comment;
import com.app.my.domain.models.Product;
import com.app.my.domain.repositories.CommentRepository;
import com.app.my.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    private final CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;

    //Получаем страницу с товарами и формой для создания товара
    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        Iterable<Comment> comments = commentRepository.findAll();
        model.addAttribute("comments", comments);
        return "products";
    }


    @GetMapping("/comment/add")
    public String commentAdd(Model model) {
        return "commentAdd";
    }
    //Добавляем комментарии
    @PostMapping("/comment/add")
    public String productCommentAdd(@RequestParam String text, Principal principal, Model model) throws IOException{
        Comment comment = new Comment(text);
        commentService.addComment(principal, comment);
        return "redirect:/";
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