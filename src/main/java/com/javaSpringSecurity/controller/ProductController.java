package com.javaSpringSecurity.controller;

import com.javaSpringSecurity.dto.Product;
import com.javaSpringSecurity.entity.UserInfo;
import com.javaSpringSecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@EnableMethodSecurity
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // it specify that this method or api can only be accessed by the admin(Basant)
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")//it specifies that this method or api can only be accessed by the user(John)
    public Product getProductById(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @PostMapping("/new")
    public String adduser(@RequestBody UserInfo userInfo) {
        return productService.addUser(userInfo);
    }
}
