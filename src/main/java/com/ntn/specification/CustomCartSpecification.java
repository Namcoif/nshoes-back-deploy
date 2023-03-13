package com.ntn.specification;

import com.ntn.entity.Cart;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
public class CustomCartSpecification implements Specification<Cart> {

    @NonNull
    private String field;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<Cart> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (field.equalsIgnoreCase("userId")) {
            return criteriaBuilder.equal(root.get("user").get("userId"), value);
        }

        if (field.equalsIgnoreCase("productId")) {
            return criteriaBuilder.equal(root.get("product"), value);
        }
        return null;
    }
}
