package com.app.my.domain.repositories;

import com.app.my.domain.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRespository extends JpaRepository<Image, Long> {

}
