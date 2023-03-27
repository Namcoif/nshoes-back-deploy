package com.ntn.specification;

import com.ntn.entity.Product;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
public class CustomProductSpecification implements Specification<Product> {

    @NonNull
    private String feild;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (feild.equalsIgnoreCase("productName")) {
            return criteriaBuilder.like(root.get("productName"), "%" + value + "%");
        }
        if (feild.equalsIgnoreCase("categoryId")) {
            return criteriaBuilder.equal(root.get("category"), value);
        }

        if (feild.equalsIgnoreCase("maxPrice")) {
            return criteriaBuilder.between(root.get("promotionPrice").as(Float.class), (float) 0, (Float) value);
        }

        if (feild.equalsIgnoreCase("minPrice")) {
            return criteriaBuilder.between(root.<Float>get("promotionPrice"), (Float) value, (float) 10000000);
        }
        
        return null;
    }
}
