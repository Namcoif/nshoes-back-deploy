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
        if (queryOrderDTO != null && queryOrderDTO.getOrderStatus() != null) {
            CustomOrderSpecification orderStatus = new CustomOrderSpecification("orderStatus", queryOrderDTO.getOrderStatus());
            if (where != null) {
                where = where.and(orderStatus);
            } else {
                where = orderStatus;
            }

        }
        return where;
    }
}
