package com.ntn.dto;

import lombok.Data;
@Data
public class CartDTO {

    private Integer id;

    private Integer quantity;
    private Integer size;
    private UserDTO2 user;

    private ProductDTO2 product;
    @Data
    static class ProductDTO2{
        private int id;
        private String productName;
    }

    @Data
    static class UserDTO2{
        private String id;
        private String fullName;
    }
}
