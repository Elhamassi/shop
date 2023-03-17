package com.example.shop.shop.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.shop.shop.exceptions.ProductNotFoundException;
import com.example.shop.shop.model.Product;
import com.example.shop.shop.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products")
    List<Product> getAll(){
        return productService.getAllProducts();
    }

    @GetMapping(value = "/products/{id}")
    ResponseEntity<Product> getById(@PathVariable("id") @Min(1) int id){
        Product getPrd = productService.findById(id).orElseThrow(()->new ProductNotFoundException("Product Not Found!"));
        return ResponseEntity.ok().body(getPrd);
    }

    @PostMapping(value = "/products")
    ResponseEntity<?> createProduct(@Valid @RequestBody Product newprd){
        Product added = productService.save(newprd);
        URI location  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping(value = "/products/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable("id") @Min(1) int id, @Valid @RequestBody Product newprd){
        Product prd = productService.findById(id).orElseThrow(()->new ProductNotFoundException("Product Not found!"));
        productService.save(prd);
        return ResponseEntity.ok().body(prd);
    }

    @DeleteMapping(value = "/products/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable("id") @Min(1) int id){
        Product prd = productService.findById(id).orElseThrow(()->new ProductNotFoundException("Product Not found!"));
        productService.delete(prd.getId());
        return ResponseEntity.ok().body("Product ID: "+prd.getId()+" Deleted with success!");
    }
}
