package com.ntn.dto;

import com.ntn.entity.Order;
import com.ntn.entity.Product;
import lombok.Data;

@Data
public class OrderDetailsDTO {
    private Integer id;

    private Integer quantity;

    private Float productPrice;

    private String productName;

    private String productUrl;

    private Order order;

    private Product product;
}
