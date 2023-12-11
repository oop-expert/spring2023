package com.app.my.app.services;

import com.app.my.domain.models.Comment;
import com.app.my.domain.models.User;
import com.app.my.domain.repositories.CommentRepository;
import com.app.my.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    //метод добавляет комментарий, авторизованного пользователя
    public void addComment(Principal principal, Comment comment) throws IOException {
        comment.setUser(getUserByPrincipal(principal));
        log.info("Saving new comment");
        commentRepository.save(comment);
    }

    // Метод удаляет комментарий
    public void banComment(Long id) throws IOException{
        commentRepository.deleteById(id);
    }

    //Получаем авторизованного пользователя
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
}
