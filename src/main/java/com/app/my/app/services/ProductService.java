package com.app.my.app.services;

import com.app.my.domain.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private List<Product> products = new ArrayList<>();
    private long ID = 0;

    //Тестовое добавление элементов в список продуктов
    {
        products.add(new Product(++ID, "Молоко", "Просто молоко", 67));
        products.add(new Product(++ID, "Хлеб", "Просто хлеб", 30));
    }

    //Метод возвращает весь товар, который имеется
    public List<Product> listProducts() {
        logger.info("You've just got a list of products ");
        return products;
    }

    //Метод добавляет товар в список
    public void saveProduct(Product product){
        product.setId(++ID);
        products.add(product);
        logger.info("You have just saved the next product: " + product.getTitle() + " " + product.getDescription());
    }

    //Метод удаления товара
    public void deleteProduct(Long id){
        logger.info("You have just deleted the product with the next id: " + id);
        products.removeIf(product -> product.getId().equals(id));
    }

    //Метод возвращает товар по id
    public Product getProductById(Long id){
        for (Product product : products) {
            if (product.getId().equals(id)) {
                logger.info("You have just received the next product: " + product.getTitle() + " " + product.getDescription());
                return product;
            }
        }
        return null;
    }
}
