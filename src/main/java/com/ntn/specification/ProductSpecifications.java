package com.ntn.specification;

import com.ntn.dto.QueryProductDTO;
import com.ntn.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ProductSpecifications {
    public static Specification<Product> searchProducts(QueryProductDTO queryProductDTO) {
        Specification<Product> where = null;
        if (queryProductDTO != null && !StringUtils.isEmpty(queryProductDTO.getProductName())) {
            queryProductDTO.getProductName().trim();
            CustomProductSpecification productName = new CustomProductSpecification("productName", queryProductDTO.getProductName());

            where = Specification.where(productName);
        }

        if (queryProductDTO != null && queryProductDTO.getCategoryId() != null) {
            CustomProductSpecification categoryId = new CustomProductSpecification("categoryId", queryProductDTO.getCategoryId());
            if (where != null) {
                where = where.and(categoryId);
            } else where = categoryId;
        }

        if (queryProductDTO != null && queryProductDTO.getMinPrice() != null) {
            CustomProductSpecification minPrice = new CustomProductSpecification("minPrice", queryProductDTO.getMinPrice());
            if (where != null) {
                where = where.and(minPrice);
            } else where = minPrice;
        }
        if (queryProductDTO != null && queryProductDTO.getMaxPrice() != null) {
            CustomProductSpecification maxPrice = new CustomProductSpecification("maxPrice", queryProductDTO.getMaxPrice());
            if (where != null) {
                where = where.and(maxPrice);
            } else where = maxPrice;
        }
        return where;
    }
}
