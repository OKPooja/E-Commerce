package com.example.Ecommerce.controllers;

import com.example.Ecommerce.models.Product;
import com.example.Ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //CORS
@RequestMapping("/api") //All api's will by default have this in url
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/")
    public String home() {
        return "Working";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/products/{prodId}")
    public Product getProduct(@PathVariable int prodId) {
        return service.getProductById(prodId);
    }
}
