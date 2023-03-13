package com.ntn.dto;

import com.ntn.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @Data
    public static class ProductSize{
        private Integer id;
        private Integer size;
    }
    @Data
    static  class ProductImage{
        private Integer id;
        private String url;
    }
}
