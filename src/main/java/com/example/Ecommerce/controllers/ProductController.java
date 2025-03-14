package com.example.Ecommerce.controllers;

import com.example.Ecommerce.models.Product;
import com.example.Ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/add_product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) throws IOException {
        Product product1 = service.addProduct(product, imageFile);
        if(product1 != null) {
            return new ResponseEntity<>(product1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/products/{prodId}/image")
    public ResponseEntity<?> getImageById(@PathVariable int prodId) {
        Product product = service.getProductById(prodId);
        byte []imageFile = product.getImageData();
        return ResponseEntity.ok().body(imageFile);
    }

    @PutMapping("/update_product/{prodId}")
    public ResponseEntity<?> updateProduct(@PathVariable int prodId, @RequestPart Product product, @RequestPart MultipartFile imageFile) throws IOException {
        Product product1 = service.updateProduct(prodId, product, imageFile);
        if(product1 != null) return new ResponseEntity<>(product1, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete_product/{prodId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int prodId) {
        Product product = service.getProductById(prodId);;
        if(product != null) {
            service.deleteProduct(prodId);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product id doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }
}
