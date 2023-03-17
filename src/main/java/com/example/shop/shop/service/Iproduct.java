package com.example.shop.shop.service;

import java.util.List;
import java.util.Optional;

import com.example.shop.shop.model.Product;

public interface Iproduct {
    List<Product> getAllProducts();
    Optional<Product> findById(int id);
    Product save(Product newprd);
    void delete(int id);
}
