package com.example.Ecommerce.controllers;

import com.example.Ecommerce.models.Product;
import com.example.Ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{prodId}")
    public ResponseEntity<Product> getProduct(@PathVariable int prodId) {
        Product product = service.getProductById(prodId);
        if(product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
