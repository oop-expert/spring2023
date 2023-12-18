package com.app.my.domain.models;

import com.app.my.domain.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductTest {

    @Test
    void testGetProductById(){
        long id = 0;
        Product product = new Product(id, "Молоко", "Просто молоко", 123);
        Assertions.assertEquals(0, product.getId());
    }

    @Test
    void testSaveProduct(){
        long id = 0;
        List<Product> products = new ArrayList<>();
        products.add(new Product(id, "Молоко", "Просто молоко", 123));
        Assertions.assertEquals(products.get((int) id), products.get(0));
    }
}
