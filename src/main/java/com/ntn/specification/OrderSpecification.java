package com.ntn.specification;

import com.ntn.dto.QueryOrderDTO;
import com.ntn.entity.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {

    public static Specification<Order> searchOrders(QueryOrderDTO queryOrderDTO) {
        Specification<Order> where = null;

        if (queryOrderDTO != null && queryOrderDTO.getUserId() != null) {
            CustomOrderSpecification userId = new CustomOrderSpecification("userId", queryOrderDTO.getUserId());
            where = Specification.where(userId);

        }
        return where;
    }
}
