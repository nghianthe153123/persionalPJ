package com.example.demo.service;

import com.example.demo.dto.FieldSearchDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(long id) {
        return productRepository.findById(id).get();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public Page<Product> searchProducts(List<FieldSearchDTO> searchDTOs, Pageable pageable) {
        Specification<Product> spec = null;

        if (searchDTOs != null && !searchDTOs.isEmpty()) {
            spec = createSpecification(searchDTOs);
        }

        if (spec != null)
            return productRepository.findAll(spec, pageable);

        return productRepository.findAll(pageable);

    }

    private Specification<Product> createSpecification(List<FieldSearchDTO> searchDTOs) {
        if (searchDTOs == null || searchDTOs.isEmpty()) {
            return null;
        }

        Specification<Product> spec = new ProductSpecification(searchDTOs.get(0));

        for (int i = 1; i < searchDTOs.size(); i++) {
            FieldSearchDTO searchDTO = searchDTOs.get(i);
            spec = spec.and(new ProductSpecification(searchDTO));
        }

        return spec;
    }
}
