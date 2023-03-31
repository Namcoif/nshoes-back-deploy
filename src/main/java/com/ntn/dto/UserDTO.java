package com.ntn.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private String userId;

    private String email;

    private String username;

    private String fullName;

    private String password;

    private String status;

    private RoleDTO userRole;

    private List<Cart> carts;

    //    private Integer role;
    @Data
    public static class RoleDTO {
        private Integer id;
        private String role;
    }

    @Data
    static class Cart {
        private Integer id;
        private Integer size;
        private Integer quantity;
        private Product2 product;
    }

    @Data
    static class Product2 {
        private Integer productId;
        private String productName;
        private List<Image2> productImgUrls;
        private Float promotionPrice;

    }

    @Data
    static class Image2 {
        private String url;
    }

}
