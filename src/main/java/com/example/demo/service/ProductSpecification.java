package com.example.demo.service;

import com.example.demo.dto.FieldSearchDTO;
import com.example.demo.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification implements Specification<Product> {
    private final FieldSearchDTO searchDTO;

    public ProductSpecification(FieldSearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (searchDTO.getOperation()) {
            case EQUAL:
                return criteriaBuilder.equal(root.get(searchDTO.getField()), searchDTO.getValue());
            case LIKE:
                return criteriaBuilder.like(root.get(searchDTO.getField()), "%" + searchDTO.getValue() + "%");
            case GREATER_THAN:
                try {
                    Integer value = Integer.parseInt(searchDTO.getValue());
                    return criteriaBuilder.greaterThan(root.get(searchDTO.getField()), value);
                } catch (NumberFormatException e) {
                    return criteriaBuilder.conjunction();
                }
            case LESS_THAN:
                try {
                    Integer value = Integer.parseInt(searchDTO.getValue());
                    return criteriaBuilder.lessThan(root.get(searchDTO.getField()), value);
                } catch (NumberFormatException e) {
                    return criteriaBuilder.conjunction();
                }

            default:
                return null;
        }
    }
}
