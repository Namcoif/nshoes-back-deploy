package com.ntn.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {

    private Integer id;

    private Date createdDate;

    private Date receivedDate;

    private String orderStatus;

    private String address;

    private String phoneNumber;

    private Integer quantity;

    private Integer size;

    private Float productPrice;

    private String userId;

    private Product product;

    @Data
    static class Product {
        private Integer id;
        private String productName;

    }
}
