package com.app.my.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    //id комментария
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Поле содержит в себе сам текст комментария
    private String text;

    //Поле содержит автора комментария
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    //Поле для отслеживания даты создания комментария
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public Comment(String text){
        this.text = text;
    }
}
