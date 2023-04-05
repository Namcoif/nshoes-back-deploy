package com.ntn.service;

import com.ntn.dto.QueryOrderDTO;
import com.ntn.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    List<Order> getListOrders();

    Page<Order> getListOrdersPaging(Pageable pageable, QueryOrderDTO queryOrderDTO);

    void createOrder(Order order);

    Order getOrderById(Integer id);

    void updateOrder(Order order);

    Page<Order> getListOrdersPaging(Pageable pageable);
}
