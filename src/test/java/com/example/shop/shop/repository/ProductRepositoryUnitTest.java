package com.example.shop.shop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.shop.shop.model.Product;


@DataJpaTest
class ProductRepositoryUnitTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    void findAll_should_return_employee_list() {
        // When
        List<Product> products = this.productRepository.findAll();
        // Then
        assertEquals(4, products.size());
    }
    
}
