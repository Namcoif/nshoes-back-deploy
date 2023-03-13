package com.ntn.dto;

import lombok.Data;

@Data
public class QueryProductDTO {
    private String search;
    private Integer categoryId;
    private Float minPrice = (float) 0.0;
    private Float maxPrice;


}
