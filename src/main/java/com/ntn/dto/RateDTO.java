package com.ntn.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RateDTO {
    private Integer id;
    private Integer star;
    private String comment;
    private Date rateDate;

    private int productId;

    private String userId;
}
