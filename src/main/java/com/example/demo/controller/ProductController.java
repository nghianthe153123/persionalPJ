package com.example.demo.controller;

import com.example.demo.dto.FieldSearchDTO;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.dto.FieldSearchDTO.SearchOperation.LIKE;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping()
    public String getProducts(@PageableDefault(size = 10) Pageable pageable, Model model) {
        List<FieldSearchDTO> searchDTOs = null;
        Page<Product> products = productService.searchProducts(searchDTOs, pageable);
        model.addAttribute("products", products);
        return "list-product";
    }
//
//    @PutMapping
//    public Product updateProduct(@RequestBody Product product) {
//        return productService.save(product);
//    }
//
//    @DeleteMapping
//    public void deleteProduct(@RequestBody Product product) {
//        productService.delete(product);
//    }
//
//    @PostMapping
//    public Product addProduct(@RequestBody @Valid Product product) {
//        log.trace(product.getName());
//        return productService.save(product);
//    }

    @PostMapping("/search")
    public String searchProducts(
            @RequestParam String name,
            @PageableDefault(size = 10) Pageable pageable,
            Model model) {
        List<FieldSearchDTO> searchDTOs = new ArrayList<>();
        searchDTOs.add(new FieldSearchDTO("name", name, LIKE));
        Page<Product> products = productService.searchProducts(searchDTOs, pageable);
        model.addAttribute("products", products);
        return "list-product";
    }


}
