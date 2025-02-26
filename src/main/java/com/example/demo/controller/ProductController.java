package com.example.demo.controller;

import com.example.demo.dto.FieldSearchDTO;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody Product product) {
        productService.delete(product);
    }

    @PostMapping
    public Product addProduct(@RequestBody @Valid Product product) {
        System.out.println(product.getName());
        log.trace(product.getName());
        return productService.save(product);
    }
    @PostMapping("/search")
    public ResponseEntity<Page<Product>> searchProducts(
            @RequestBody List<FieldSearchDTO> searchDTOs,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<Product> products = productService.searchProducts(searchDTOs, pageable);
        return ResponseEntity.ok(products);
    }


}
