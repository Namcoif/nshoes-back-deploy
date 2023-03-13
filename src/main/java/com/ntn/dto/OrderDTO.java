package com.ntn.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {

    private Integer id;

    private Date createdDate;

    private Date receivedDate;

    private String orderStatus;

    private String userId;

    private Integer productId;

    @Data
    static class Product {
        private Integer id;
        private String productName;

    }
}
