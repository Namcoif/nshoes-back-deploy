package com.ntn.service;

import com.ntn.dto.QueryOrderDTO;
import com.ntn.entity.Order;
import com.ntn.repository.IOrderRepository;
import com.ntn.specification.OrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public List<Order> getListOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> getListOrdersPaging(Pageable pageable, QueryOrderDTO queryOrderDTO) {
        Specification<Order> where = OrderSpecification.searchOrders(queryOrderDTO);
        return orderRepository.findAll(where, pageable);
    }

    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Integer id) {
        return getOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }


}
