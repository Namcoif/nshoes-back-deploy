package com.ntn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer id;

    private String productName;

    private Float originalPrice;

    private Float promotionPrice;

    private Date createdDate;

    private Integer quantity;

    private String description;

    private String productStatus;

    private Integer soldCount;

    private Integer categoryId;

    private List<ProductSize> productSizes;

    private List<ProductImage> productImgUrls;

    private List<RateProduct> productRates;

    @Data
    public static class ProductSize {
        private Integer id;
        private Integer size;
    }

    @Data
    public static class ProductImage {
        private Integer id;
        private String url;
    }

    @Data
    public static class RateProduct {
        private Integer id;
        private Integer star;
        private String comment;
        private Date rateDate;
        private UserRate user;

        @Data
        static class UserRate {
            private String fullName;
        }
    }
}
