package com.example.shop.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.shop.model.Product;
import com.example.shop.shop.repository.ProductRepository;

@Service
public class ProductService implements Iproduct{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product newprd) {
        return productRepository.save(newprd);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }
    
}
