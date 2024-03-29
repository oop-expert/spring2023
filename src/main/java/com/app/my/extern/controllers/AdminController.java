package com.app.my.extern.controllers;

import com.app.my.app.services.CommentService;
import com.app.my.app.services.UserService;
import com.app.my.domain.models.Category;
import com.app.my.domain.models.Comment;
import com.app.my.domain.models.User;
import com.app.my.domain.models.enums.Role;
import com.app.my.domain.repositories.CategoryRepository;
import com.app.my.domain.repositories.CommentRepository;
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
import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    private final CommentService commentService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/admin")
    public String admin(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        Iterable<Comment> comments = commentRepository.findAll();
        model.addAttribute("commentaries", comments);
        model.addAttribute("categories", categories);
        model.addAttribute("users", userService.list());
        return "admin";
    }
    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form, String name) {
        userService.changeUserRoles(user, form);
        userService.changeUserName(user, name);
        return "redirect:/admin";
    }

    @PostMapping("/admin/comment/ban/{id}")
    public String commentBan(@PathVariable("id") Long id) throws IOException {
        commentRepository.deleteById(id);
        return "redirect:/admin";
    }

}
