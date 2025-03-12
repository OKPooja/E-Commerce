package com.example.Ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") //All api's will by default have this in url
public class ProductController {

    @GetMapping("/")
    public String homePage() {
        return "Working";
    }
}
