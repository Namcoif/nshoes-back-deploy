package com.ntn.specification;

import com.ntn.dto.QueryCartDTO;
import com.ntn.entity.Cart;
import org.springframework.data.jpa.domain.Specification;

public class CartSpecification {

    public static Specification<Cart> getListCartsByUserId(String id) {

        Specification<Cart> where = null;

        if (id != null) {
            CustomCartSpecification userId = new CustomCartSpecification("userId", id);

            where = Specification.where(userId);
        }

        return where;
    }

    public static Specification<Cart> getListCartsByProductId(Integer id) {

        Specification<Cart> where = null;

        if (id != null) {
            CustomCartSpecification productId = new CustomCartSpecification("productId", id);

            where = Specification.where(productId);
        }

        return where;
    }

    public static Specification<Cart> searchCarts(QueryCartDTO queryCartDTO) {

        Specification<Cart> where = null;

        if (queryCartDTO.getProductId() != null) {
            CustomCartSpecification productId = new CustomCartSpecification("productId", queryCartDTO.getProductId());

            where = Specification.where(productId);
        }
        if (queryCartDTO.getUserId() != null) {
            CustomCartSpecification userId = new CustomCartSpecification("userId", queryCartDTO.getUserId());
            if (where == null) where = userId;
            else
                where = where.and(userId);
        }

        return where;
    }
}
